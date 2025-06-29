package com.example.hematin.data.repository

import com.example.hematin.domain.models.UserModel
import com.example.hematin.domain.repository.UserRepository
import com.example.hematin.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {
    companion object {
        private const val USERS_COLLECTION = "users"
    }

    override suspend fun createUser(user: UserModel): Resource<Unit> {
        return try {
            firestore.collection(USERS_COLLECTION).document(user.uid)
                .set(user).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Gagal membuat pengguna di database.")
        }
    }

    override suspend fun getUser(uid: String): Resource<UserModel> {
        return try {
            val document = firestore.collection(USERS_COLLECTION).document(uid).get().await()
            val user = document.toObject(UserModel::class.java)
            if (user != null) {
                Resource.Success(user)
            } else {
                Resource.Error("Pengguna tidak ditemukan di database.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Gagal mengambil data pengguna.")
        }
    }

    override suspend fun updateUser(user: UserModel): Resource<Unit> {
        return try {
            firestore.collection(USERS_COLLECTION).document(user.uid)
                .set(user, SetOptions.merge()).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Gagal memperbarui data pengguna.")
        }
    }

}