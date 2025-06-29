package com.example.hematin.domain.usecase

import com.example.hematin.domain.repository.OnboardingRepository
import javax.inject.Inject

class SaveOnboardingStatus @Inject constructor (
    private val onboardingRepository: OnboardingRepository
){
    suspend operator fun invoke(isCompleted: Boolean) {
        onboardingRepository.saveOnboardingStatus(isCompleted)
    }
}