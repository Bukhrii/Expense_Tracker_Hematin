package com.example.hematin.presentation.ui.components.Icons

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.hematin.R

@Composable
fun DownloadButtonIcon() {
    IconButton(
        onClick = {}
    ) {
        Icon(
            imageVector = Icons.Filled.FileDownload,
            contentDescription = null,
            tint = colorResource(R.color.primary_dark_green),
            modifier = Modifier.size(25.dp)
        )
    }
}