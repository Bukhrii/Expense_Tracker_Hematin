package com.example.hematin.presentation.ui.components.bottomNav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hematin.presentation.ui.navigation.Screen

@Composable
fun BottomNavigation(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
                .background(Color.White)
                .height(70.dp)) {
            IconButton(onClick = {
                navController.navigate(Screen.HomeScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                    tint = if (currentRoute == Screen.HomeScreen.route) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(onClick = {
                navController.navigate(Screen.StatisticScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Outlined.BarChart,
                    contentDescription = null,
                    tint = if (currentRoute == Screen.StatisticScreen.route) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(onClick = {
                navController.navigate(Screen.TransactionListScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Outlined.AccountBalanceWallet,
                    contentDescription = null,
                    tint = when (currentRoute) {
                        Screen.TransactionListScreen.route -> MaterialTheme.colorScheme.primary
                        Screen.TransactionDetailScreen.route -> MaterialTheme.colorScheme.primary
                        Screen.AddTransactionScreen.route -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.onSurfaceVariant},
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(onClick = {
                navController.navigate(Screen.ProfileScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = if (currentRoute == Screen.ProfileScreen.route) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(35.dp)
                )
            }

        }
        FloatingActionButton(onClick = {
            navController.navigate(Screen.AddTransactionScreen.route)
        },
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.offset(y = (-40).dp)
        ) {
            Icon(imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = Color.White)
        }
    }
}