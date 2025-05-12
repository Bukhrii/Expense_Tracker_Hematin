package com.example.hematin.presentation.ui.screens.insight

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ThumbsUpDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.hematin.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.hematin.presentation.ui.components.Icons.BackButtonIcon
import com.example.hematin.presentation.ui.components.Icons.DownloadButtonIcon
import com.example.hematin.presentation.ui.components.bottomNav.BottomNavigation
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.Line

@Composable
fun StatisticScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                            .background(Color.White)
                            .height(100.dp)
                            .padding(horizontal = 25.dp, vertical = 40.dp)) {
                        BackButtonIcon()
                        Text(
                            text = stringResource(R.string.statistic),
                            fontWeight = FontWeight.Bold
                        )
                        DownloadButtonIcon()
                    }
                }
                     },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(PaddingValues(
                            start = 25.dp,
                            end = 25.dp,
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        ))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(vertical = 30.dp).fillMaxWidth()) {
                        TextButton(
                            onClick = {

                            },
                            colors = ButtonDefaults.textButtonColors(contentColor = Color.Gray)
                        ) {
                            Text(
                                text = stringResource(R.string.day),
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 14.sp
                            )
                        }
                        TextButton(
                            onClick = {

                            },
                            colors = ButtonDefaults.textButtonColors(contentColor = Color.Gray)
                        ) {
                            Text(
                                text = stringResource(R.string.week),
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 14.sp
                            )
                        }
                        TextButton(
                            onClick = {

                            },
                            colors = ButtonDefaults.textButtonColors(contentColor = Color.Gray)
                        ) {
                            Text(
                                text = stringResource(R.string.month),
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 14.sp
                            )
                        }
                        TextButton(
                            onClick = {

                            },
                            colors = ButtonDefaults.textButtonColors(contentColor = Color.Gray)
                        ) {
                            Text(
                                text = stringResource(R.string.year),
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 14.sp
                            )
                        }
                    }

                    val expenseLabel = stringResource(R.string.expenses)
                    val chartColor = colorResource(R.color.secondary_lighter_green)
                    LineChart(
                        modifier = Modifier.fillMaxWidth().height(180.dp).padding(horizontal = 22.dp),
                        data = remember {
                            listOf(
                                Line(
                                    label = expenseLabel,
                                    values = listOf(42.5, 456.2, 45.1, 3.8),
                                    color = SolidColor(chartColor),
                                    firstGradientFillColor = Color(0xFF2BC0A1).copy(alpha = .5f),
                                    secondGradientFillColor = Color.Transparent,
                                    strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                                    gradientAnimationDelay = 1000,
                                    drawStyle = DrawStyle.Stroke(width = 2.dp),
                                )
                            )
                        },
                        animationMode = AnimationMode.Together(delayBuilder = {
                            it * 500L
                        }),
                    )
                    Spacer(modifier = Modifier.padding(vertical = 24.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.top_spending),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        IconButton(
                            onClick = {

                            },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ThumbsUpDown,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(35.dp)
                            )
                        }
                    }

                    LazyColumn {
                        items(5) {
                                index ->
                            Row(horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 8.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .height(80.dp)
                                    .background(colorResource(R.color.secondary_lightest_green))) {
                                Column {
                                    Text(
                                        text = stringResource(R.string.name_record),
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = stringResource(R.string.date),
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontSize = 12.sp
                                    )
                                }
                                Text(
                                    text = stringResource(R.string.value_expense),
                                    color = Color.Red,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                BottomNavigation(navController = navController)
            }
        )
    }
}