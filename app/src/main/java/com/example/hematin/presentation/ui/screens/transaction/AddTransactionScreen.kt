package com.example.hematin.presentation.ui.screens.transaction

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.ImportExport
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hematin.R
import com.example.hematin.domain.models.TransactionModel
import com.example.hematin.presentation.ui.components.DateInput
import com.example.hematin.presentation.ui.components.Icons.BackButtonIconOnprimary
import com.example.hematin.presentation.ui.components.bottomNav.BottomNavigation
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    navController: NavController,
    transactionId: String?,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val addState = viewModel.state.value.addState
    val listState = viewModel.state.value.listState
    val context = LocalContext.current

    val isEditMode = transactionId != null

    val existingTransaction by remember(transactionId, listState.transactions) {
        derivedStateOf {
            if (isEditMode) {
                listState.transactions.find { it.id == transactionId }
            } else {
                null
            }
        }
    }

    var transactionName by remember { mutableStateOf("") }
    var transactionAmount by remember { mutableStateOf("") }
    var transactionStatus by remember { mutableStateOf("") }
    var transactionAccount by remember { mutableStateOf("") }
    var transactionCategory by remember { mutableStateOf("") }

    val datePickerState = rememberDatePickerState()

    var expandedStatus by remember { mutableStateOf(false) }
    val listStatus = listOf("Pemasukan", "Pengeluaran")
    var expandedAccount by remember { mutableStateOf(false) }
    val listAccount = listOf("Dompet", "Bank", "E-Wallet")
    var expandedCategory by remember { mutableStateOf(false) }
    val listCategory = listOf("Makanan", "Transportasi", "Belanja", "Hiburan", "Gaji", "Lainnya")

    LaunchedEffect(existingTransaction) {
        existingTransaction?.let {
            transactionName = it.title
            transactionAmount = it.amount.toLong().toString()
            transactionStatus = it.transactionType
            transactionAccount = it.account
            transactionCategory = it.category
            datePickerState.selectedDateMillis = it.date.time
        }
    }

    LaunchedEffect(addState) {
        if (addState.addSuccess) {
            val message = if (isEditMode) "Transaksi berhasil diperbarui!" else "Transaksi berhasil ditambahkan!"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.onAddSuccessShown()
            navController.popBackStack()
        }
        addState.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.onAddSuccessShown()
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    BottomNavigation(navController = navController)
                }
            }
        ) { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(R.drawable.ic_topbar),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Column(
                        modifier = Modifier.padding(top = 80.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                BackButtonIconOnprimary(onClick = { navController.popBackStack() })
                                Spacer(modifier = Modifier.padding(25.dp))
                                Text(
                                    text = if (isEditMode) "Edit Transaksi" else "Tambah Transaksi",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(vertical = 14.dp))
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                            colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.background),
                            shape = RoundedCornerShape(36.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                                .offset(y = 20.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 30.dp, horizontal = 30.dp)
                            ) {
                                OutlinedTextField(
                                    value = transactionName,
                                    onValueChange = { transactionName = it },
                                    label = { Text(stringResource(R.string.name_transaction)) },
                                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.padding(16.dp))

                                ExposedDropdownMenuBox(expanded = expandedStatus, onExpandedChange = { expandedStatus = !expandedStatus }) {
                                    OutlinedTextField(
                                        value = transactionStatus,
                                        onValueChange = {},
                                        readOnly = true,
                                        label = { Text(stringResource(R.string.status_transaction)) },
                                        leadingIcon = { Icon(Icons.Default.ImportExport, contentDescription = null) },
                                        trailingIcon = { Icon(if (expandedStatus) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, null) },
                                        modifier = Modifier.fillMaxWidth().menuAnchor()
                                    )
                                    ExposedDropdownMenu(expanded = expandedStatus, onDismissRequest = { expandedStatus = false }) {
                                        listStatus.forEach { selectionOption ->
                                            DropdownMenuItem(text = { Text(selectionOption) }, onClick = {
                                                transactionStatus = selectionOption
                                                expandedStatus = false
                                            })
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.padding(16.dp))

                                ExposedDropdownMenuBox(expanded = expandedCategory, onExpandedChange = { expandedCategory = !expandedCategory }) {
                                    OutlinedTextField(
                                        value = transactionCategory,
                                        onValueChange = {},
                                        readOnly = true,
                                        label = { Text("Kategori") },
                                        leadingIcon = { Icon(Icons.Default.Category, contentDescription = null) },
                                        trailingIcon = { Icon(if (expandedCategory) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, null) },
                                        modifier = Modifier.fillMaxWidth().menuAnchor()
                                    )
                                    ExposedDropdownMenu(expanded = expandedCategory, onDismissRequest = { expandedCategory = false }) {
                                        listCategory.forEach { selectionOption ->
                                            DropdownMenuItem(text = { Text(selectionOption) }, onClick = {
                                                transactionCategory = selectionOption
                                                expandedCategory = false
                                            })
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.padding(16.dp))

                                ExposedDropdownMenuBox(expanded = expandedAccount, onExpandedChange = { expandedAccount = !expandedAccount }) {
                                    OutlinedTextField(
                                        value = transactionAccount,
                                        onValueChange = {},
                                        readOnly = true,
                                        label = { Text("Akun") },
                                        leadingIcon = { Icon(Icons.Default.AccountBalanceWallet, contentDescription = null) },
                                        trailingIcon = { Icon(if (expandedAccount) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, null) },
                                        modifier = Modifier.fillMaxWidth().menuAnchor()
                                    )
                                    ExposedDropdownMenu(expanded = expandedAccount, onDismissRequest = { expandedAccount = false }) {
                                        listAccount.forEach { selectionOption ->
                                            DropdownMenuItem(text = { Text(selectionOption) }, onClick = {
                                                transactionAccount = selectionOption
                                                expandedAccount = false
                                            })
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.padding(16.dp))

                                OutlinedTextField(
                                    value = transactionAmount,
                                    onValueChange = {
                                        if (it.all { char -> char.isDigit() }) {
                                            transactionAmount = it
                                        }
                                    },
                                    label = { Text(stringResource(R.string.amount_transaction)) },
                                    leadingIcon = { Text("Rp", modifier = Modifier.padding(start = 12.dp), style = MaterialTheme.typography.bodyLarge) },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.padding(16.dp))

                                DateInput(datePickerState)
                                Spacer(modifier = Modifier.padding(16.dp))

                                Button(
                                    onClick = {
                                        val selectedDate = datePickerState.selectedDateMillis?.let { Date(it) } ?: Date()
                                        val amountDouble = transactionAmount.toDoubleOrNull() ?: 0.0
                                        val userId = viewModel.getCurrentUserId()

                                        val transactionToSave = if (isEditMode) {
                                            TransactionModel(
                                                id = transactionId!!,
                                                userId = userId,
                                                title = transactionName,
                                                amount = amountDouble,
                                                transactionType = transactionStatus,
                                                date = selectedDate,
                                                category = transactionCategory,
                                                account = transactionAccount
                                            )
                                        } else {
                                            TransactionModel(
                                                userId = userId,
                                                title = transactionName,
                                                amount = amountDouble,
                                                transactionType = transactionStatus,
                                                date = selectedDate,
                                                category = transactionCategory,
                                                account = transactionAccount
                                            )
                                        }

                                        if (isEditMode) {
                                            viewModel.updateTransaction(transactionToSave)
                                        } else {
                                            viewModel.addTransaction(transactionToSave)
                                        }
                                    },
                                    enabled = !addState.isLoading,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    if (addState.isLoading) {
                                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                                    } else {
                                        Text(if (isEditMode) "Simpan Perubahan" else "Simpan")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}