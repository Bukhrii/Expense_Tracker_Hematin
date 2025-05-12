package com.example.hematin.presentation.ui.screens.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.hematin.domain.models.OnboardingModel

@Composable
fun OnboardingGraphic(onboardingModel: OnboardingModel) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.size(120.dp)
        )

        LoaderIntro(
            modifier = Modifier
                .size(300.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            image = onboardingModel.image
        )

        Spacer(
            modifier = Modifier.size(60.dp)
        )

        Text(
            text = stringResource(id = onboardingModel.title),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 19.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(10.dp)
        )

        Text(
            text = stringResource(id = onboardingModel.description),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(20.dp)
        )
    }
}


@Composable
fun LoaderIntro(modifier: Modifier, image: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(image))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}