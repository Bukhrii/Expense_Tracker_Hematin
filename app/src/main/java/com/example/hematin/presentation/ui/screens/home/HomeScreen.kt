package com.example.hematin.presentation.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.hematin.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hematin.presentation.ui.components.bottomNav.BottomNavigation
import com.example.hematin.presentation.ui.navigation.Screen
import com.example.hematin.presentation.ui.screens.profile.ProfileViewModel
import com.example.hematin.presentation.ui.screens.transaction.TransactionViewModel
import com.example.hematin.util.formatAmount
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavController,
    transactionViewModel: TransactionViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val transactionListState = transactionViewModel.state.value.listState
    val profileState = profileViewModel.state.value

    val totalBalance = transactionListState.transactions.sumOf { if (it.transactionType == "Pemasukan") it.amount else -it.amount }
    val totalIncome = transactionListState.transactions.filter { it.transactionType == "Pemasukan" }.sumOf { it.amount }
    val totalExpense = transactionListState.transactions.filter { it.transactionType == "Pengeluaran" }.sumOf { it.amount }

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = paddingValues.calculateBottomPadding())
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_topbar),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillWidth
                        )

                        Column(modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 80.dp, bottom = 30.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column {
                                    Text(
                                        stringResource(R.string.greetings),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Text(
                                        text = profileState.user?.username ?: "User",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.padding(vertical = 14.dp))

                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier.fillMaxWidth().height(220.dp)
                            ) {
                                Column(modifier = Modifier.padding(20.dp)) {
                                    Text(
                                        text = stringResource(R.string.total_balance),
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Text(
                                        text = "Rp ${formatAmount(totalBalance)}",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        fontSize = 30.sp
                                    )
                                    Spacer(modifier = Modifier.padding(15.dp))
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    imageVector = Icons.Filled.ArrowCircleDown,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier.size(30.dp)
                                                )
                                                Spacer(modifier = Modifier.padding(2.dp))
                                                Text(
                                                    text = stringResource(R.string.income),
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    color = MaterialTheme.colorScheme.onPrimary,
                                                    fontSize = 14.sp
                                                )
                                            }
                                            Text(
                                                text = "Rp ${formatAmount(totalIncome)}",
                                                style = MaterialTheme.typography.titleLarge,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                fontSize = 20.sp
                                            )
                                        }
                                        Column {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    imageVector = Icons.Filled.ArrowCircleUp,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier.size(30.dp)
                                                )
                                                Spacer(modifier = Modifier.padding(2.dp))
                                                Text(
                                                    text = stringResource(R.string.expenses),
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    color = MaterialTheme.colorScheme.onPrimary,
                                                    fontSize = 14.sp
                                                )
                                            }
                                            Text(
                                                text = "Rp ${formatAmount(totalExpense)}",
                                                style = MaterialTheme.typography.titleLarge,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                fontSize = 20.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp, end = 25.dp, top = 16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string.transaction_history),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            TextButton(
                                onClick = { navController.navigate(Screen.TransactionListScreen.route) },
                                colors = ButtonDefaults.textButtonColors(contentColor = Color.Gray)
                            ) {
                                Text(
                                    text = stringResource(R.string.see_all),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        transactionListState.transactions.take(5).forEach { transaction ->
                            val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
                            val dateString = sdf.format(transaction.date)
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Column {
                                    Text(
                                        text = transaction.title,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                    Text(
                                        text = "${transaction.category} • $dateString",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )
                                }
                                Text(
                                    text = "Rp ${formatAmount(transaction.amount)}",
                                    color = if (transaction.transactionType == "Pemasukan") MaterialTheme.colorScheme.primary else Color.Red,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                BottomNavigation(navController = navController)
            }
        )
    }
}