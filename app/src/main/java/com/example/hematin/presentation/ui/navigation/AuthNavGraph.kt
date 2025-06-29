package com.example.hematin.presentation.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hematin.presentation.ui.screens.auth.LoginScreen
import com.example.hematin.presentation.ui.screens.auth.SignupScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = "auth_graph",
        startDestination = Screen.LoginScreen.route
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main_graph") {
                        popUpTo("auth_graph") { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignupScreen.route)
                }
            )
        }
        composable(Screen.SignupScreen.route) {
            SignupScreen(
                onSignUpSuccessToLogin = {
                    // Setelah registrasi, kembali ke halaman login
                    navController.popBackStack()
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}