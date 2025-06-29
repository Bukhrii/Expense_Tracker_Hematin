package com.example.hematin.domain.usecase

import com.example.hematin.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke(uid: String) = repository.getUser(uid)
}