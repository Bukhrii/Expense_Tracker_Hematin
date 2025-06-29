package com.example.hematin.presentation.ui.screens.insight

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hematin.R
import com.example.hematin.presentation.ui.components.Icons.BackButtonIcon
import com.example.hematin.presentation.ui.navigation.Screen
import com.example.hematin.presentation.ui.screens.transaction.StatisticData
import com.example.hematin.presentation.ui.screens.transaction.TransactionViewModel
import com.example.hematin.util.formatAmount
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticScreen(
    navController: NavController,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val state by viewModel.state
    val statisticState = state.statisticState
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Pengeluaran", "Pemasukan")

    Surface(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Scaffold(
            topBar = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 48.dp)
                ) {
                    BackButtonIcon(onClick = { navController.popBackStack() })
                    Text(
                        "Statistik",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(48.dp))
                }
            }
        ) { paddingValues ->
            if (state.listState.transactions.isEmpty() && !state.listState.isLoading) {
                EmptyState(navController, paddingValues)
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp)
                ) {
                    TabRow(selectedTabIndex = selectedTabIndex) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = { Text(title) }
                            )
                        }
                    }

                    Crossfade(targetState = selectedTabIndex) { tabIndex ->
                        when (tabIndex) {
                            0 -> StatisticContent(statisticData = statisticState.expenseStats, type = "Pengeluaran")
                            1 -> StatisticContent(statisticData = statisticState.incomeStats, type = "Pemasukan")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatisticContent(statisticData: StatisticData, type: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        item {
            Text(
                "$type 7 Hari Terakhir",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            val chartColor = if (type == "Pengeluaran") Color.Red else MaterialTheme.colorScheme.primary
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                data = remember(statisticData.chartData) {
                    listOf(
                        Line(
                            label = type,
                            values = statisticData.chartData.map { it.value.toDouble() },
                            color = SolidColor(chartColor),
                            firstGradientFillColor = chartColor.copy(alpha = .4f),
                            secondGradientFillColor = Color.Transparent,
                            strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                            gradientAnimationDelay = 1000,
                            drawStyle = DrawStyle.Stroke(width = 3.dp),
                        )
                    )
                },
                labelProperties = LabelProperties(
                    enabled = true,
                    labels = statisticData.chartData.map { it.label }
                ),
                animationMode = AnimationMode.Together(delayBuilder = { it * 100L }),
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Kategori $type Teratas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (statisticData.topCategories.isNotEmpty()) {
            items(statisticData.topCategories) { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Category,
                        contentDescription = "Kategori",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(8.dp),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = category.category,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "Rp ${formatAmount(category.amount)}",
                        color = if (type == "Pengeluaran") Color.Red else MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        } else {
            item {
                Text(
                    "Belum ada data $type.",
                    modifier = Modifier.padding(vertical = 16.dp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun EmptyState(navController: NavController, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Belum Ada Riwayat Transaksi",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Mulai catat transaksimu untuk melihat analisis keuangan di sini.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate(Screen.AddTransactionScreen.route + "?transactionId=-1") }) {
            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tambah Transaksi Pertama")
        }
    }
}