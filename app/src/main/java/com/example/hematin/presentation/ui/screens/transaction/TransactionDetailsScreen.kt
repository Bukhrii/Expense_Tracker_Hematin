package com.example.hematin.presentation.ui.screens.transaction

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.hematin.presentation.ui.components.Icons.BackButtonIconOnprimary
import com.example.hematin.presentation.ui.components.Icons.NotificationButtonIcon
import com.example.hematin.presentation.ui.components.bottomNav.BottomNavigation

@Composable
fun TransactionDetailScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxWidth()) {
                    BottomNavigation(navController = navController)
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),

                ) {
                Image(painter = painterResource(R.drawable.ic_topbar), contentDescription = null, modifier = Modifier.fillMaxWidth())

                Column(
                    modifier = Modifier.padding(
                        top = 80.dp,
                    )
                ) {
                    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            BackButtonIconOnprimary()
                            Text(
                                text = stringResource(R.string.detail_transaction),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            NotificationButtonIcon()
                        }
                    }


                    Spacer(modifier = Modifier.padding(vertical = 14.dp))

                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 20.dp
                        ),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(36.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .offset(y = 20.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 30.dp, horizontal = 20.dp)) {
                            Text(
                                text = stringResource(R.string.income),
                                style = MaterialTheme.typography.bodyMedium,
                                color = colorResource(R.color.primary_green),
                                fontSize = 20.sp,
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = stringResource(R.string.total_transaction_value),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray,
                                fontSize = 40.sp
                            )
                            Spacer(modifier = Modifier.padding(14.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(R.string.transaction_history),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                IconButton(
                                    onClick = {

                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.KeyboardArrowUp,
                                        contentDescription = null,
                                        tint = Color.DarkGray,
                                        modifier = Modifier.size(25.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(R.string.status_transaction),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = stringResource(R.string.status_transaction_value),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = colorResource(R.color.primary_green),
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(R.string.from_transaction),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = stringResource(R.string.from_transaction_value),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(R.string.time_transaction),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = stringResource(R.string.time_transaction_value),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(R.string.date_transaction),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = stringResource(R.string.date_transaction_value),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(R.string.earnings_transaction),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = stringResource(R.string.earnings_transaction_value),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(R.string.fee_transaction),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = stringResource(R.string.fee_transaction_value),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(R.string.total_transaction),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = stringResource(R.string.total_transaction_value),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))

                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                                OutlinedButton(
                                    onClick = {},
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = colorResource(R.color.primary_green)
                                    ),
                                    border = BorderStroke(1.dp, colorResource(R.color.primary_green))
                                ) {
                                    Text(text = stringResource(R.string.edit_transaction))
                                }
                                OutlinedButton(
                                    onClick = {},
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = Color.Red
                                    ),
                                    border = BorderStroke(1.dp, Color.Red)
                                ) {
                                    Text(text = stringResource(R.string.delete_transaction))
                                }
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                        }
                    }
                }
            }
        }
    }
}