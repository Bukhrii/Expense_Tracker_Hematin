package com.example.hematin.presentation.ui.components.Icons

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.hematin.R

@Composable
fun MoreButtonIcon() {
    IconButton(onClick = {

    }, modifier = Modifier.size(20.dp)) {
        Icon(
            imageVector = Icons.Filled.MoreHoriz,
            contentDescription = null,
            tint = colorResource(R.color.white),
            modifier = Modifier.size(30.dp)
        )
    }
}