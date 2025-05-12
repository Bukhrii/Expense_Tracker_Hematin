package com.example.hematin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.hematin.presentation.ui.navigation.AppNavHost
import com.example.hematin.presentation.ui.theme.HematinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            HematinTheme {
                AppNavHost()
            }
        }
    }
}