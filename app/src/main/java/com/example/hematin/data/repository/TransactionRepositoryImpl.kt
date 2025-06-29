package com.example.hematin.data.repository

import com.example.hematin.data.local.database.dao.TransactionDao
import com.example.hematin.data.local.toDomain
import com.example.hematin.data.local.toEntity
import com.example.hematin.domain.models.TransactionModel
import com.example.hematin.domain.repository.TransactionRepository
import com.example.hematin.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val transactionDao: TransactionDao
) : TransactionRepository {

    private var firestoreListener: ListenerRegistration? = null

    override fun startSync() {
        // Hentikan listener lama jika ada untuk mencegah kebocoran
        stopSync()
        auth.currentUser?.uid?.let { userId ->
            firestoreListener = firestore.collection("users").document(userId)
                .collection("transactions")
                .orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener { snapshots, e ->
                    if (e != null) {
                        return@addSnapshotListener
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        val firestoreTransactions = snapshots?.toObjects(TransactionModel::class.java) ?: emptyList()
                        transactionDao.deleteAllByUserId(userId)
                        // Gunakan loop karena DAO Anda tidak punya insertAll
                        firestoreTransactions.forEach {
                            transactionDao.insert(it.toEntity())
                        }
                    }
                }
        }
    }

    override fun stopSync() {
        firestoreListener?.remove()
        firestoreListener = null
    }

    override suspend fun addTransaction(transaction: TransactionModel): Resource<Unit> {
        return try {
            val userId = auth.currentUser?.uid ?: return Resource.Error("Pengguna belum login.")
            val transactionWithUser = transaction.copy(userId = userId)

            // Simpan ke Firestore
            firestore.collection("users").document(userId)
                .collection("transactions").add(transactionWithUser).await()

            // Room akan terupdate otomatis oleh listener
            Resource.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Gagal menambahkan transaksi.")
        }
    }

    override fun getTransactions(): Flow<List<TransactionModel>> {
        val userId = auth.currentUser?.uid ?: return kotlinx.coroutines.flow.flowOf(emptyList())
        // Selalu ambil data dari Room yang sudah tersinkronisasi
        return transactionDao.getAllByUserId(userId).map { transactions ->
            transactions.map { it.toDomain() }
        }
    }

    override suspend fun updateTransaction(transaction: TransactionModel): Resource<Unit> {
        return try {
            val userId = auth.currentUser?.uid ?: return Resource.Error("Pengguna belum login.")
            val firestoreDoc = firestore.collection("users").document(userId)
                .collection("transactions").whereEqualTo("id", transaction.id).get().await().documents.firstOrNull()

            firestoreDoc?.reference?.set(transaction)?.await()
            // Room akan terupdate otomatis oleh listener
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Gagal memperbarui transaksi.")
        }
    }

    override suspend fun deleteTransaction(transaction: TransactionModel): Resource<Unit> {
        return try {
            val userId = auth.currentUser?.uid ?: return Resource.Error("Pengguna belum login.")
            // PERBAIKAN: Langsung hapus dokumen berdasarkan ID-nya, jangan query.
            firestore.collection("users").document(userId)
                .collection("transactions").document(transaction.id)
                .delete().await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Gagal menghapus transaksi.")
        }
    }

    // PERBAIKAN: Ubah parameter ID menjadi String
    override fun getTransactionById(id: String): Flow<TransactionModel?> {
        return transactionDao.getById(id).map { it?.toDomain() }
    }
}