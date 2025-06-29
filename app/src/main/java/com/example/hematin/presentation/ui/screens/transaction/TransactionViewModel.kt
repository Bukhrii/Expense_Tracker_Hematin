package com.example.hematin.presentation.ui.screens.transaction

import android.os.Bundle
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hematin.domain.models.TransactionModel
import com.example.hematin.domain.repository.TransactionRepository
import com.example.hematin.domain.usecase.AddTransactionUseCase
import com.example.hematin.domain.usecase.DeleteTransactionUseCase
import com.example.hematin.domain.usecase.GetTransactionsUseCase
import com.example.hematin.domain.usecase.UpdateTransactionUseCase
import com.example.hematin.util.Resource
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject


data class ChartEntry(val label: String, val value: Float)
data class TopSpending(val category: String, val amount: Double, val percentage: Float)

data class StatisticData(
    val chartData: List<ChartEntry> = emptyList(),
    val topCategories: List<TopSpending> = emptyList(),
    val totalAmount: Double = 0.0,
    val dailyAverage: Double = 0.0,
    val topCategory: String = "-"
)

data class StatisticState(
    val expenseStats: StatisticData = StatisticData(),
    val incomeStats: StatisticData = StatisticData()
)

data class TransactionListState(
    val transactions: List<TransactionModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class AddTransactionState(
    val isLoading: Boolean = false,
    val addSuccess: Boolean = false,
    val error: String? = null
)

data class TransactionState(
    val addState: AddTransactionState = AddTransactionState(),
    val listState: TransactionListState = TransactionListState(),
    val statisticState: StatisticState = StatisticState(),
    val selectedTransaction: TransactionModel? = null,
    val isLoadingDetails: Boolean = false
)


@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val addTransactionUseCase: AddTransactionUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val auth: FirebaseAuth,
    private val transactionRepository: TransactionRepository,
    private val analytics: FirebaseAnalytics
) : ViewModel() {

    private val _state = mutableStateOf(TransactionState())
    val state: State<TransactionState> = _state

    init {
        transactionRepository.startSync()
        getTransactions()
    }

    private fun getTransactions() {
        _state.value = _state.value.copy(listState = _state.value.listState.copy(isLoading = true))
        getTransactionsUseCase()
            .onEach { transactions ->
                processStatistics(transactions)
                _state.value = _state.value.copy(
                    listState = TransactionListState(transactions = transactions, isLoading = false)
                )
            }
            .catch { e ->
                _state.value = _state.value.copy(
                    listState = TransactionListState(error = e.localizedMessage ?: "Terjadi error", isLoading = false)
                )
            }
            .launchIn(viewModelScope)
    }

    private fun processStatistics(transactions: List<TransactionModel>) {
        val expenses = transactions.filter { it.transactionType == "Pengeluaran" }
        val expenseStats = processCategoryStatistics(expenses)
        val incomes = transactions.filter { it.transactionType == "Pemasukan" }
        val incomeStats = processCategoryStatistics(incomes)
        _state.value = _state.value.copy(
            statisticState = StatisticState(
                expenseStats = expenseStats,
                incomeStats = incomeStats
            )
        )
    }

    private fun processCategoryStatistics(transactions: List<TransactionModel>): StatisticData {
        val dateFormat = SimpleDateFormat("EEE", Locale("id", "ID"))

        val sevenDaysAgo = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, -6)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

        val recentTransactions = transactions.filter { !it.date.before(sevenDaysAgo) }
        val totalAmountForPeriod = recentTransactions.sumOf { it.amount }

        val dailyTotals = (0..6).map { i ->
            val cal = Calendar.getInstance()
            cal.add(Calendar.DAY_OF_YEAR, -i)
            val dayName = dateFormat.format(cal.time)

            val dayStart = cal.apply { set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 0) }.time
            val dayEnd = cal.apply { set(Calendar.HOUR_OF_DAY, 23); set(Calendar.MINUTE, 59); set(Calendar.SECOND, 59) }.time

            val total = recentTransactions
                .filter { it.date >= dayStart && it.date <= dayEnd }
                .sumOf { it.amount }
            dayName to total
        }.reversed().toMutableList()

        val chartData = dailyTotals.map { ChartEntry(it.first, it.second.toFloat()) }

        val topCategories = recentTransactions
            .filter { it.category.isNotBlank() }
            .groupBy { it.category.trim().lowercase(Locale.getDefault()) }
            .map { (lowerCaseCategory, transactionsInCategory) ->
                val totalAmount = transactionsInCategory.sumOf { it.amount }
                val originalCategoryName = transactionsInCategory.first().category
                val percentage = if (totalAmountForPeriod > 0) (totalAmount / totalAmountForPeriod * 100).toFloat() else 0f
                TopSpending(originalCategoryName, totalAmount, percentage)
            }
            .sortedByDescending { it.amount }
            .take(5)

        val dailyAverage = if (recentTransactions.isNotEmpty()) totalAmountForPeriod / 7 else 0.0
        val topCategoryName = topCategories.firstOrNull()?.category ?: "-"

        return StatisticData(chartData, topCategories, totalAmountForPeriod, dailyAverage, topCategoryName)
    }

    fun getTransactionDetails(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingDetails = true)
            transactionRepository.getTransactionById(id).collect { transaction ->
                _state.value = _state.value.copy(
                    selectedTransaction = transaction,
                    isLoadingDetails = false
                )
            }
        }
    }

    fun addTransaction(transaction: TransactionModel) {
        viewModelScope.launch {
            _state.value = _state.value.copy(addState = AddTransactionState(isLoading = true))
            if (transaction.title.isBlank() || transaction.amount <= 0 || transaction.transactionType.isBlank() || transaction.category.isBlank() || transaction.account.isBlank()) {
                _state.value = _state.value.copy(addState = AddTransactionState(error = "Semua field wajib diisi dengan benar."))
                return@launch
            }
            when (addTransactionUseCase(transaction)) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(addState = AddTransactionState(addSuccess = true))
                    val params = Bundle().apply {
                        putString("category", transaction.category)
                        putDouble("amount", transaction.amount)
                    }
                    analytics.logEvent("add_transaction", params)
                }
                is Resource.Error -> _state.value = _state.value.copy(addState = AddTransactionState(error = "Gagal menambah transaksi"))
                else -> {}
            }
        }
    }

    fun updateTransaction(transaction: TransactionModel) {
        viewModelScope.launch {
            _state.value = _state.value.copy(addState = AddTransactionState(isLoading = true))
            if (transaction.title.isBlank() || transaction.amount <= 0 || transaction.transactionType.isBlank() || transaction.category.isBlank() || transaction.account.isBlank()) {
                _state.value = _state.value.copy(addState = AddTransactionState(error = "Semua field wajib diisi dengan benar."))
                return@launch
            }
            when (updateTransactionUseCase(transaction)) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(addState = AddTransactionState(addSuccess = true))
                    val params = Bundle().apply {
                        putString("transaction_id", transaction.id)
                    }
                    analytics.logEvent("update_transaction", params)
                }
                is Resource.Error -> _state.value = _state.value.copy(addState = AddTransactionState(error = "Gagal memperbarui transaksi"))
                else -> {}
            }
        }
    }

    fun deleteTransaction(transaction: TransactionModel) {
        viewModelScope.launch {
            deleteTransactionUseCase(transaction)
            val params = Bundle().apply {
                putString("transaction_id", transaction.id)
            }
            analytics.logEvent("delete_transaction", params)
        }
    }

    fun onAddSuccessShown() {
        _state.value = _state.value.copy(addState = _state.value.addState.copy(addSuccess = false, error = null))
    }

    fun getCurrentUserId(): String {
        return auth.currentUser?.uid ?: ""
    }

    override fun onCleared() {
        super.onCleared()
        transactionRepository.stopSync()
    }
}