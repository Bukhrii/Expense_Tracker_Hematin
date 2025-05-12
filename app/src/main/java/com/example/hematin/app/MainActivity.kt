package com.example.hematin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hematin.domain.models.OnboardingModel
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.hematin.presentation.ui.screens.home.HomeScreen
import com.example.hematin.presentation.ui.screens.home.TransactionListScreen
import com.example.hematin.presentation.ui.screens.insight.StatisticScreen
import com.example.hematin.presentation.ui.screens.login.LoginScreen
import com.example.hematin.presentation.ui.screens.onboarding.OnboardingScreen
import com.example.hematin.presentation.ui.screens.resetpw.ResetPasswordScreen
import com.example.hematin.presentation.ui.screens.signup.SignupScreen
import com.example.hematin.presentation.ui.theme.HematinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            HematinTheme {
                TransactionListScreen()
            }
        }
    }
}