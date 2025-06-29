package com.example.hematin.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    suspend fun saveOnboardingStatus(isCompleted: Boolean)
    fun getOnboardingStatus(): Flow<Boolean>
}