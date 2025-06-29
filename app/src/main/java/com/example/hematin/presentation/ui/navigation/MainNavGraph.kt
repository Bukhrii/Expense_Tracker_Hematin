package com.example.hematin.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hematin.presentation.ui.screens.home.HomeScreen
import com.example.hematin.presentation.ui.screens.insight.StatisticScreen
import com.example.hematin.presentation.ui.screens.profile.ProfileScreen
import com.example.hematin.presentation.ui.screens.transaction.AddTransactionScreen
import com.example.hematin.presentation.ui.screens.transaction.TransactionDetailScreen
import com.example.hematin.presentation.ui.screens.transaction.TransactionListScreen
import com.example.hematin.presentation.ui.screens.profile.ProfileViewModel
import com.example.hematin.presentation.ui.screens.transaction.TransactionViewModel

@Composable
fun MainNavGraph(navController: NavHostController, onLogout: () -> Unit) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        route = "main_graph_internal"
    ) {
        // --- Layar Utama ---
        composable(Screen.HomeScreen.route) {
            val transactionViewModel: TransactionViewModel = hiltViewModel()
            val profileViewModel: ProfileViewModel = hiltViewModel()
            HomeScreen(
                navController = navController,
                transactionViewModel = transactionViewModel,
                profileViewModel = profileViewModel
            )
        }

        // --- Layar Statistik ---
        composable(Screen.StatisticScreen.route) {
            StatisticScreen(navController)
        }

        // --- Layar Tambah/Edit Transaksi ---
        composable(
            route = Screen.AddTransactionScreen.route + "?transactionId={transactionId}",
            arguments = listOf(
                navArgument("transactionId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getString("transactionId")
            AddTransactionScreen(
                navController = navController,
                transactionId = transactionId
            )
        }

        // --- PERBAIKAN: Ini adalah definisi yang hilang untuk TransactionDetailScreen ---
        composable(
            route = Screen.TransactionDetailScreen.route + "/{transactionId}",
            arguments = listOf(navArgument("transactionId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getString("transactionId")
            val viewModel: TransactionViewModel = hiltViewModel()

            // Panggil ViewModel untuk mengambil data. Ini adalah cara yang aman.
            LaunchedEffect(transactionId) {
                if (transactionId != null) {
                    viewModel.getTransactionDetails(transactionId)
                }
            }

            TransactionDetailScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        // --------------------------------------------------------------------------

        // --- Layar Daftar Transaksi ---
        composable(Screen.TransactionListScreen.route) {
            val viewModel: TransactionViewModel = hiltViewModel()
            TransactionListScreen(navController, viewModel)
        }

        // --- Layar Profil ---
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(
                navController = navController,
                onLogout = onLogout
            )
        }
    }
}