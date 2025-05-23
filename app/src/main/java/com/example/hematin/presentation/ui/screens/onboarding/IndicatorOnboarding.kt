package com.example.hematin.presentation.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.hematin.R

@Composable
fun IndicatorOnboarding(
    pageSize: Int,
    currentPage: Int,
    selectedColor: Color = colorResource(R.color.primary_dark_green),
    unselectedColor: Color = colorResource(R.color.secondary_light_green)
) {

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) {
            Spacer(modifier = Modifier.size(5.dp))

            Box(modifier = Modifier
                .height(14.dp)
                .width(width = if (it == currentPage) 32.dp else 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = if(it == currentPage) selectedColor else unselectedColor)
            )
        }
    }
    Spacer(modifier = Modifier.padding(20.dp))
}