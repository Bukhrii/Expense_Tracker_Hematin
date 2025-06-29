package com.example.hematin.data.repository

import com.example.hematin.domain.repository.OnboardingRepository
import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnboardingRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : OnboardingRepository {
    companion object {
        const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
    }

    override suspend fun saveOnboardingStatus(isCompleted: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_ONBOARDING_COMPLETED, isCompleted).apply()
    }

    override fun getOnboardingStatus(): Flow<Boolean> {
        return flow{
            emit(sharedPreferences.getBoolean(KEY_ONBOARDING_COMPLETED, false))
        }
    }
}