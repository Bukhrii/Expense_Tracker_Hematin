package com.example.hematin.presentation.ui.screens.main

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.hematin.presentation.ui.components.bottomNav.BottomNavigation
import com.example.hematin.presentation.ui.navigation.MainNavGraph
import com.example.hematin.presentation.ui.screens.auth.AuthState
import com.example.hematin.presentation.ui.screens.auth.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    onLogout: () -> Unit
) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.observeAsState()
    val context = LocalContext.current


    LaunchedEffect(authState) {
        if (authState is AuthState.Unauthenticated) {
            Toast.makeText(context, "Logout berhasil!", Toast.LENGTH_SHORT).show()
            onLogout()
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        MainNavGraph(navController = navController, onLogout = onLogout)
    }
}