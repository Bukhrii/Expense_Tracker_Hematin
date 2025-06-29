package com.example.hematin.presentation.ui.screens.transaction

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
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

// Data class untuk Statistik
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


// Data class untuk State Utama
data class TransactionListState(
    val transactions: List<TransactionModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class TransactionState(
    val addState: AddTransactionState = AddTransactionState(),
    val listState: TransactionListState = TransactionListState(),
    val statisticState: StatisticState = StatisticState(),
    val selectedTransaction: TransactionModel? = null, // <-- TAMBAHKAN INI
    val isLoadingDetails: Boolean = false // <-- Tambahan untuk loading indicator
)

data class TransactionState(
    val addState: AddTransactionState = AddTransactionState(),
    val listState: TransactionListState = TransactionListState(),
    val statisticState: StatisticState = StatisticState()
)


@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val addTransactionUseCase: AddTransactionUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val auth: FirebaseAuth,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _state = mutableStateOf(TransactionState())
    val state: State<TransactionState> = _state

    init {
        transactionRepository.startSync()
        getTransactions()
    }

    private fun getTransactions() {
        // Set loading untuk listState
        _state.value = _state.value.copy(listState = _state.value.listState.copy(isLoading = true))

        getTransactionsUseCase()
            .onEach { transactions ->
                // Proses data statistik setiap kali ada pembaruan
                processStatistics(transactions)

                // Update listState dengan data baru dan set isLoading menjadi false
                _state.value = _state.value.copy(
                    listState = TransactionListState(transactions = transactions, isLoading = false)
                )
            }
            .catch { e ->
                // Tangani error
                _state.value = _state.value.copy(
                    listState = TransactionListState(error = e.localizedMessage ?: "Terjadi error", isLoading = false)
                )
            }
            .launchIn(viewModelScope)
    }

    private fun processStatistics(transactions: List<TransactionModel>) {
        // Proses untuk Pengeluaran
        val expenses = transactions.filter { it.transactionType == "Pengeluaran" }
        val expenseStats = processCategoryStatistics(expenses, "Pengeluaran")

        // Proses untuk Pemasukan
        val incomes = transactions.filter { it.transactionType == "Pemasukan" }
        val incomeStats = processCategoryStatistics(incomes, "Pemasukan")

        // Update state statistik
        _state.value = _state.value.copy(
            statisticState = StatisticState(
                expenseStats = expenseStats,
                incomeStats = incomeStats
            )
        )
    }

    // Fungsi helper untuk memproses statistik berdasarkan tipe
    private fun processCategoryStatistics(transactions: List<TransactionModel>, type: String): StatisticData {
        val totalAmountForPeriod = transactions.sumOf { it.amount }
        val dateFormat = SimpleDateFormat("EEE", Locale("id", "ID"))
        val calendar = Calendar.getInstance()

        // Inisialisasi 7 hari terakhir dengan nilai 0
        val dailyTotals = mutableMapOf<String, Double>()
        val daysOfWeek = (0..6).map { i ->
            val cal = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -i) }
            dateFormat.format(cal.time)
        }.reversed()
        daysOfWeek.forEach { day -> dailyTotals[day] = 0.0 }

        // Akumulasi total dalam 7 hari terakhir
        val sevenDaysAgo = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -6) }.time
        transactions.filter { it.date.after(sevenDaysAgo) }.forEach {
            val day = dateFormat.format(it.date)
            if (dailyTotals.containsKey(day)) {
                dailyTotals[day] = dailyTotals.getValue(day) + it.amount
            }
        }
        val chartData = daysOfWeek.map { day -> ChartEntry(day, dailyTotals[day]?.toFloat() ?: 0f) }

        // Proses data untuk Top Kategori
        val topCategories = transactions
            .groupBy { it.category }
            .mapValues { entry -> entry.value.sumOf { it.amount } }
            .map {
                val percentage = if (totalAmountForPeriod > 0) (it.value / totalAmountForPeriod * 100).toFloat() else 0f
                TopSpending(it.key, it.value, percentage)
            }
            .sortedByDescending { it.amount }
            .take(5)

        val dailyAverage = if (totalAmountForPeriod > 0) totalAmountForPeriod / 7 else 0.0
        val topCategoryName = topCategories.firstOrNull()?.category ?: "-"

        return StatisticData(
            chartData = chartData,
            topCategories = topCategories,
            totalAmount = totalAmountForPeriod,
            dailyAverage = dailyAverage,
            topCategory = topCategoryName
        )
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
                is Resource.Success -> _state.value = _state.value.copy(addState = AddTransactionState(addSuccess = true))
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
                is Resource.Success -> _state.value = _state.value.copy(addState = AddTransactionState(addSuccess = true))
                is Resource.Error -> _state.value = _state.value.copy(addState = AddTransactionState(error = "Gagal memperbarui transaksi"))
                else -> {}
            }
        }
    }

    fun deleteTransaction(transaction: TransactionModel) {
        viewModelScope.launch {
            deleteTransactionUseCase(transaction)
        }
    }

    fun onAddSuccessShown() {
        _state.value = _state.value.copy(addState = _state.value.addState.copy(addSuccess = false))
    }

    fun getCurrentUserId(): String {
        return auth.currentUser?.uid ?: ""
    }

    override fun onCleared() {
        super.onCleared()
        transactionRepository.stopSync()
    }
}