package com.example.hematin.domain.models

data class TransactionModel(
    val title: String,
    val date: java.util.Date,
    val amount: Double,
    val category: String,
    val account: String,
    val transactionType: String
)
