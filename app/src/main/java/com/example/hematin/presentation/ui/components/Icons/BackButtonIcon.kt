package com.example.hematin.presentation.ui.components.Icons

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.hematin.R

@Composable
fun BackButtonIcon() {
    IconButton(
        onClick = {

        }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = null,
            tint = colorResource(R.color.primary_dark_green),
            modifier = Modifier.size(25.dp)
        )
    }

}

@Composable
fun BackButtonIconOnprimary() {
    IconButton(
        onClick = {

        }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(25.dp)
        )
    }

}