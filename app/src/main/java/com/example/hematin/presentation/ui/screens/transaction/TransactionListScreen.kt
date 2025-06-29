package com.example.hematin.presentation.ui.screens.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.hematin.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hematin.presentation.ui.components.Icons.BackButtonIconOnprimary
import com.example.hematin.presentation.ui.components.bottomNav.BottomNavigation
import com.example.hematin.presentation.ui.navigation.Screen
import com.example.hematin.util.formatAmount
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TransactionListScreen(
    navController: NavController,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val state by viewModel.state
    val listState = state.listState

    val totalBalance = listState.transactions.sumOf { if (it.transactionType == "Pemasukan") it.amount else -it.amount }

    Surface(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Scaffold(
            bottomBar = {
                BottomNavigation(navController = navController)
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = paddingValues.calculateBottomPadding())
            ) {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(R.drawable.ic_topbar),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillWidth
                        )
                        Column(modifier = Modifier.padding(top = 80.dp, start = 25.dp, end = 25.dp, bottom = 30.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                BackButtonIconOnprimary(onClick = { navController.popBackStack() })

                                Spacer(modifier = Modifier.padding(25.dp))
                                Text(
                                    text = stringResource(R.string.transaction_list),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                            }
                        }
                    }
                }

                item {
                    Card(
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.background),
                        shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-130).dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(40.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.total_balance),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 20.sp
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = "Rp ${formatAmount(totalBalance)}",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 40.sp
                            )
                            Spacer(modifier = Modifier.padding(8.dp))

                            if (listState.isLoading) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }

                items(listState.transactions) { transaction ->
                    val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
                    val dateString = sdf.format(transaction.date)
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp, vertical = 12.dp)
                            .offset(y = (-120).dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = transaction.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "${transaction.category} • $dateString",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Rp. ${formatAmount(transaction.amount)}",
                            color = if (transaction.transactionType == "Pemasukan") MaterialTheme.colorScheme.primary else Color.Red,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(
                            onClick = {
                                navController.navigate(Screen.TransactionDetailScreen.route + "/${transaction.id}")
                            },
                            modifier = Modifier.width(90.dp)
                        ) {
                            Text(text = stringResource(R.string.detail), fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}