package com.example.hematin.presentation.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hematin.R
import com.example.hematin.domain.models.OnboardingModel
import com.example.hematin.presentation.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(navController: NavController) {

    val pages = listOf(OnboardingModel.FirstPages, OnboardingModel.SecondPages, OnboardingModel.ThirdPages)
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val scope = rememberCoroutineScope()

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Selanjutnya")
                1 -> listOf("Kembali", "Selanjutnya")
                2 -> listOf("Kembali", "Mulai")
                else -> listOf("", "")
            }
        }
    }

    Scaffold(
        content = {
            Column(Modifier.padding(it)) {
                HorizontalPager(state = pagerState) { index ->
                    OnboardingGraphic(onboardingModel = pages[index])
                }
            }
        },

        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    ButtonOnboarding(
                        text = buttonState.value[0],
                        backgroundColor = Color.Transparent,
                        textColor = colorResource(R.color.secondary_light_green)
                        ) {
                        scope.launch {
                            if(pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    IndicatorOnboarding(
                        pageSize = pages.size,
                        currentPage = pagerState.currentPage
                    )
                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    ButtonOnboarding(
                        text = buttonState.value[1],
                        backgroundColor = colorResource(R.color.primary_green),
                        textColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        scope.launch {
                            if(pagerState.currentPage < pages.size - 1) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            } else {
                                navController.navigate(Screen.loginScreen)
                            }
                        }
                    }
                }
            }
        }
    )

}