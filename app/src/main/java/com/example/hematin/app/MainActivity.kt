package com.example.hematin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hematin.presentation.ui.navigation.AppNavHost
import com.example.hematin.presentation.ui.theme.HematinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HematinTheme {
                AppNavHost()
//                Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//                    Button(
//                        onClick = {
//                            throw RuntimeException("Test Crash from MainActivity")
//                        },
//                        modifier = Modifier.align(Alignment.BottomCenter)
//                    ) {
//                        Text("Test Crash")
//                    }
//                }

            }
        }
    }
}