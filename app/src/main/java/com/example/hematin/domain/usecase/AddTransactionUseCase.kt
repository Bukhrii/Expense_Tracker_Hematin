package com.example.hematin.domain.usecase

import com.example.hematin.domain.models.TransactionModel
import com.example.hematin.domain.repository.TransactionRepository
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
){
    suspend operator fun invoke(transaction: TransactionModel) = repository.addTransaction(transaction)
}