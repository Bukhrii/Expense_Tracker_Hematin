package com.example.hematin.domain.usecase

import com.example.hematin.domain.models.UserModel
import com.example.hematin.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserModel) = repository.updateUser(user)
}