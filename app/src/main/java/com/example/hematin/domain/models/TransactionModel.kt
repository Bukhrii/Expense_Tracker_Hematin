package com.example.hematin.domain.models

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class TransactionModel(
    @DocumentId
    val id: String = "",
    val userId: String? = null,
    val title: String = "",
    val amount: Double = 0.0,
    val category: String = "",
    val date: Date = Date(),

    val account: String = "",
    val transactionType: String = ""
)
