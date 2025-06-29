package com.example.hematin.presentation.ui.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hematin.domain.models.UserModel
import com.example.hematin.domain.usecase.GetUserUseCase
import com.example.hematin.domain.usecase.UpdateUserUseCase
import com.example.hematin.util.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileState(
    val user: UserModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val updateSuccess: Boolean = false,
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    init {
        loadUserProfile()
    }

    fun loadUserProfile() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val uid = auth.currentUser?.uid
            if (uid != null) {
                when (val result = getUserUseCase(uid)) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(user = result.data, isLoading = false)
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = result.message, isLoading = false)
                    }
                    else -> {
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            } else {
                _state.value = _state.value.copy(error = "User not authenticated", isLoading = false)
            }
        }
    }

    fun updateUserProfile(username: String, phoneNumber: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, updateSuccess = false)
            val currentUser = _state.value.user
            if (currentUser != null) {
                val updatedUser = currentUser.copy(username = username, phoneNumber = phoneNumber)
                when (val result = updateUserUseCase(updatedUser)) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            user = updatedUser,
                            isLoading = false,
                            updateSuccess = true
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message,
                            isLoading = false,
                            updateSuccess = false
                        )
                    }
                    else -> {}
                }
            } else {
                _state.value = _state.value.copy(error = "Current user data is not available for update.", isLoading = false)
            }
        }
    }

    fun onUpdateSuccessShown() {
        _state.value = _state.value.copy(updateSuccess = false)
    }
}