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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hematin.R
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
                navController.navigate(Screen.homeScreen)
            }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                    tint = if (currentRoute == Screen.homeScreen) colorResource(R.color.primary_dark_green) else Color.Gray,
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(onClick = {
                navController.navigate(Screen.statisticScreen)
            }) {
                Icon(
                    imageVector = Icons.Outlined.BarChart,
                    contentDescription = null,
                    tint = if (currentRoute == Screen.statisticScreen) colorResource(R.color.primary_dark_green) else Color.Gray,
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(onClick = {
                navController.navigate(Screen.transactionListScreen)
            }) {
                Icon(
                    imageVector = Icons.Outlined.AccountBalanceWallet,
                    contentDescription = null,
                    tint = when (currentRoute) {
                        Screen.transactionListScreen -> colorResource(R.color.primary_dark_green)
                        Screen.transactionDetailScreen -> colorResource(R.color.primary_dark_green)
                        Screen.addTransactionScreen -> colorResource(R.color.primary_dark_green)
                        else -> Color.Gray},
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(onClick = {
                navController.navigate(Screen.profileScreen)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = if (currentRoute == Screen.profileScreen) colorResource(R.color.primary_dark_green) else Color.Gray,
                    modifier = Modifier.size(35.dp)
                )
            }

        }
        FloatingActionButton(onClick = {
            navController.navigate(Screen.addTransactionScreen)
        },
            shape = CircleShape,
            containerColor = colorResource(R.color.primary_dark_green),
            modifier = Modifier.offset(y = (-40).dp)
        ) {
            Icon(imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = Color.White)
        }
    }
}