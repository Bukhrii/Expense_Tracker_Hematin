package com.example.hematin.domain.models

data class UserModel(
    val uid: String = "",
    val username: String = "",
    val email: String = "",
    val phoneNumber: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
