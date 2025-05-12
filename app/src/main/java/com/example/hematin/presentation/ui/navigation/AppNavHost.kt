package com.example.hematin.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hematin.presentation.ui.screens.home.HomeScreen
import com.example.hematin.presentation.ui.screens.insight.StatisticScreen
import com.example.hematin.presentation.ui.screens.login.LoginScreen
import com.example.hematin.presentation.ui.screens.onboarding.OnboardingScreen
import com.example.hematin.presentation.ui.screens.profile.ProfileScreen
import com.example.hematin.presentation.ui.screens.resetpw.ResetPasswordScreen
import com.example.hematin.presentation.ui.screens.signup.SignupScreen
import com.example.hematin.presentation.ui.screens.transaction.AddTransactionScreen
import com.example.hematin.presentation.ui.screens.transaction.TransactionDetailScreen
import com.example.hematin.presentation.ui.screens.transaction.TransactionListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.onboardingScreen, builder = {
      composable (Screen.onboardingScreen) {
          OnboardingScreen(navController)
      }
        composable (Screen.loginScreen) {
            LoginScreen(navController)
        }
        composable (Screen.signupScreen) {
            SignupScreen(navController)
        }
        composable (Screen.homeScreen) {
            HomeScreen(navController)
        }
        composable (Screen.resetPasswordScreen) {
            ResetPasswordScreen(navController)
        }
        composable (Screen.statisticScreen) {
            StatisticScreen(navController)
        }
        composable (Screen.addTransactionScreen) {
            AddTransactionScreen(navController)
        }
        composable (Screen.transactionDetailScreen) {
            TransactionDetailScreen(navController)
        }
        composable (Screen.transactionListScreen) {
            TransactionListScreen(navController)
        }
        composable (Screen.profileScreen) {
            ProfileScreen(navController)
        }
    })
}