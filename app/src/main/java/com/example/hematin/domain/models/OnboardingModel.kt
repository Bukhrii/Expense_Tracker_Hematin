package com.example.hematin.domain.models

import androidx.annotation.RawRes
import com.example.hematin.R


sealed class OnboardingModel(
    @RawRes val image:Int,
    val title: Int,
    val description: Int
) {

    data object FirstPages: OnboardingModel(
        image = R.raw.page1,
        title = R.string.onboarding_title_first,
        description = R.string.onboarding_description_first
    )

    data object SecondPages: OnboardingModel(
        image = R.raw.page2,
        title = R.string.onboarding_title_second,
        description = R.string.onboarding_description_second
    )

    data object ThirdPages: OnboardingModel(
        image = R.raw.page3,
        title = R.string.onboarding_title_third,
        description = R.string.onboarding_description_third
    )
}
