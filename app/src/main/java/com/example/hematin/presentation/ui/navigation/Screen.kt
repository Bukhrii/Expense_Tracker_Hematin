package com.example.hematin.presentation.ui.navigation

sealed class Screen(val route: String) {
    object OnboardingScreen : Screen("Onboarding_Screen")
    object LoginScreen : Screen("Login_Screen")
    object SignupScreen : Screen("Signup_Screen")
    object HomeScreen : Screen("Home_Screen")
    object StatisticScreen : Screen("Statistic_Screen")
    object AddTransactionScreen : Screen("AddTransaction_Screen")
    object TransactionDetailScreen : Screen("TransactionDetail_Screen")
    object TransactionListScreen : Screen("TransactionList_Screen")
    object ProfileScreen : Screen("Profile_Screen")
}