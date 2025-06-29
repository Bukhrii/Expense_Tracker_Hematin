package com.example.hematin.domain.repository

import com.example.hematin.domain.models.UserModel
import com.example.hematin.util.Resource

interface UserRepository {

    suspend fun createUser(user: UserModel): Resource<Unit>
    suspend fun getUser(uid: String): Resource<UserModel>
    suspend fun updateUser(user: UserModel): Resource<Unit>

}