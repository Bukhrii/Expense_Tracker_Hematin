package com.example.hematin.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hematin.presentation.ui.screens.auth.AuthState
import com.example.hematin.presentation.ui.screens.auth.AuthViewModel
import com.example.hematin.presentation.ui.screens.main.MainScreen
import com.example.hematin.presentation.ui.screens.onboarding.OnboardingScreen
import com.example.hematin.presentation.ui.screens.onboarding.OnboardingViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val onboardingViewModel: OnboardingViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()

    val hasCompletedOnboarding by onboardingViewModel.onboardingCompleted.collectAsState()
    val authState by authViewModel.authState.observeAsState()

    val startDestination = when {
        !hasCompletedOnboarding -> "onboarding_graph"
        authState is AuthState.Authenticated -> "main_graph"
        else -> "auth_graph"
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "onboarding_graph") {
            OnboardingScreen(onFinish = {
                navController.navigate("auth_graph") {
                    popUpTo("onboarding_graph") { inclusive = true }
                }
            })
        }

        authNavGraph(navController)

        composable(route = "main_graph") {
            MainScreen(
                onLogout = {
                    navController.navigate("auth_graph") {
                        popUpTo("main_graph") { inclusive = true }
                    }
                }
            )
        }
    }
}