package com.example.hematin.presentation.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.colorResource
import com.example.hematin.presentation.ui.components.Icons.MoreButtonIcon
import com.example.hematin.presentation.ui.components.Icons.NotificationButtonIcon
import com.example.hematin.presentation.ui.components.bottomNav.BottomNavigation

@Composable
fun TransactionListScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Image(painter = painterResource(R.drawable.ic_topbar), contentDescription = null)

                    Column(modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 80.dp, bottom = 30.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column {
                                Text(
                                    stringResource(R.string.greetings),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Text(
                                    stringResource(R.string.user),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                            NotificationButtonIcon()
                        }

                        Spacer(modifier = Modifier.padding(vertical = 14.dp))

                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 20.dp
                            ),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = colorResource(R.color.primary_green)
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Row(horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row {
                                        Text(
                                            text = stringResource(R.string.total_balance),
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.onPrimary
                                        )

                                        Icon(
                                            imageVector = Icons.Filled.KeyboardArrowUp,
                                            contentDescription = null,
                                            tint = colorResource(R.color.white),
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }
                                    MoreButtonIcon()
                                }

                                Text(
                                    text = stringResource(R.string.total_balance_value),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 30.sp
                                )

                                Spacer(modifier = Modifier.padding(15.dp))

                                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier) {
                                            Icon(
                                                imageVector = Icons.Filled.ArrowCircleDown,
                                                contentDescription = null,
                                                tint = colorResource(R.color.white),
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
                                            text = stringResource(R.string.total_balance_value),
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            fontSize = 20.sp
                                        )
                                    }

                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier) {
                                            Icon(
                                                imageVector = Icons.Filled.ArrowCircleDown,
                                                contentDescription = null,
                                                tint = colorResource(R.color.white),
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
                                            text = stringResource(R.string.total_balance_value),
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

            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(PaddingValues(
                            start = 25.dp,
                            end = 25.dp,
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        ))
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = stringResource(R.string.transaction_history),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            TextButton(
                                onClick = {

                                },
                                colors = ButtonDefaults.textButtonColors(contentColor = Color.Gray)
                            ) {
                                Text(
                                    text = stringResource(R.string.see_all),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        LazyColumn {
                            items(10) {
                                    index ->
                                Row(horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                                    Column {
                                        Text(
                                            text = stringResource(R.string.name_record),
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                        Text(
                                            text = stringResource(R.string.date),
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontSize = 14.sp
                                        )
                                    }
                                    Text(
                                        text = stringResource(R.string.value_income),
                                        color = colorResource(R.color.primary_green),
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }
                    }
                }
            },
            bottomBar = {
                Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxWidth()) {
                    BottomNavigation()
                }
            }
        )
    }
}