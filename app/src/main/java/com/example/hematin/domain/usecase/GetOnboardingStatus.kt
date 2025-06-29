package com.example.hematin.domain.usecase

import com.example.hematin.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOnboardingStatus @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return onboardingRepository.getOnboardingStatus()
    }
}