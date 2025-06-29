package com.example.hematin.domain.repository

import com.example.hematin.data.local.database.entity.Transaction
import com.example.hematin.domain.models.TransactionModel
import com.example.hematin.util.Resource
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun addTransaction(transaction: TransactionModel): Resource<Unit>
    fun getTransactions(): Flow<List<TransactionModel>>
    suspend fun updateTransaction(transaction: TransactionModel): Resource<Unit>
    suspend fun deleteTransaction(transaction: TransactionModel): Resource<Unit>
    fun getTransactionById(id: String): Flow<TransactionModel?>

    fun startSync()
    fun stopSync()
}