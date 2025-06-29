package com.example.hematin.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey
    val id: String,

    val userId: String?,
    val title: String,
    val amount: Double,
    val category: String,
    val date: Date,

    val account: String,
    val transactionType: String
)
