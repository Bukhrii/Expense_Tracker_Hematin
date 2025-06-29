package com.example.hematin.presentation.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hematin.domain.usecase.GetOnboardingStatus
import com.example.hematin.domain.usecase.SaveOnboardingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getOnboardingStatus: GetOnboardingStatus,
    private val saveOnboardingStatus: SaveOnboardingStatus
) : ViewModel() {
    private val  _onboardingCompleted = MutableStateFlow(false)
    val onboardingCompleted = _onboardingCompleted.asStateFlow()

    init {
        checkOnboardingStatus()
    }

    private fun checkOnboardingStatus() {
        viewModelScope.launch {
            getOnboardingStatus().collect { isCompleted ->
                _onboardingCompleted.value = isCompleted
            }
        }
    }

    fun onFinishOnboarding() {
        viewModelScope.launch {
            saveOnboardingStatus(true)
        }
    }
}