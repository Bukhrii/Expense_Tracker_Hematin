package com.example.hematin.domain.usecase

import com.example.hematin.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    operator fun invoke() = repository.getTransactions()
}