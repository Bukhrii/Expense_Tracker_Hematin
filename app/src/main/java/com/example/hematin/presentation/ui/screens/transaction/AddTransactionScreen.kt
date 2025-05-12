package com.example.hematin.presentation.ui.screens.transaction

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImportExport
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.hematin.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.hematin.presentation.ui.components.DateInput
import com.example.hematin.presentation.ui.components.Icons.BackButtonIconOnprimary
import com.example.hematin.presentation.ui.components.Icons.NotificationButtonIcon
import com.example.hematin.presentation.ui.components.bottomNav.BottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(navController: NavController) {
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
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            BackButtonIconOnprimary()
                            Text(
                                text = stringResource(R.string.add_transaction),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            NotificationButtonIcon()
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
                                    .padding(vertical = 30.dp, horizontal = 30.dp)) {

                                var transactionName by remember { mutableStateOf("") }
                                OutlinedTextField(
                                    value = transactionName,
                                    onValueChange = { transactionName = it },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Filled.Person,
                                            contentDescription = stringResource(id = R.string.name_record),
                                            modifier = Modifier.size(30.dp)
                                        )
                                    },
                                    label = { Text(stringResource(R.string.name_transaction)) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = colorResource(R.color.primary_green),
                                        focusedLabelColor = colorResource(R.color.primary_green)
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.padding(16.dp))

                                var expanded by remember { mutableStateOf(false) }
                                var transactionStatus by remember { mutableStateOf("") }
                                val listStatus = listOf(
                                    stringResource(R.string.income),
                                    stringResource(R.string.expenses)
                                )

                                ExposedDropdownMenuBox(
                                    expanded = expanded,
                                    onExpandedChange = { expanded = !expanded}
                                ) {
                                    OutlinedTextField(
                                        value = transactionStatus,
                                        onValueChange = { transactionStatus = it },
                                        readOnly = true,
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Filled.ImportExport,
                                                contentDescription = stringResource(id = R.string.status_transaction),
                                                modifier = Modifier.size(30.dp)
                                            )
                                        },
                                        trailingIcon = {
                                            Icon(
                                                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                                contentDescription = null
                                            )
                                        },
                                        label = { Text(stringResource(R.string.status_transaction)) },
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = colorResource(R.color.primary_green),
                                            focusedLabelColor = colorResource(R.color.primary_green)
                                        ),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                        modifier = Modifier.fillMaxWidth()
                                            .menuAnchor()
                                    )

                                    ExposedDropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        listStatus.forEach { selectionOption ->
                                            DropdownMenuItem(
                                                text = { Text(selectionOption) },
                                                onClick = {
                                                    transactionStatus = selectionOption
                                                    expanded = false
                                                }
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.padding(16.dp))

                                var transactionAmount by remember { mutableStateOf("") }
                                OutlinedTextField(
                                    value = transactionAmount,
                                    onValueChange = { transactionAmount = it },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Filled.Person,
                                            contentDescription = stringResource(id = R.string.amount_transaction),
                                            modifier = Modifier.size(30.dp)
                                        )
                                    },
                                    label = { Text(stringResource(R.string.amount_transaction)) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = colorResource(R.color.primary_green),
                                        focusedLabelColor = colorResource(R.color.primary_green)
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.padding(16.dp))

                                DateInput()
                            }
                        }
                    }
                }
            }
        }
    }
}