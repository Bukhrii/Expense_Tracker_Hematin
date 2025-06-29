package com.example.hematin.data.local

import com.example.hematin.data.local.database.entity.Transaction
import com.example.hematin.domain.models.TransactionModel

fun Transaction.toDomain(): TransactionModel {
    return TransactionModel(
        id = this.id,
        userId = this.userId,
        title = this.title,
        date = this.date,
        amount = this.amount,
        category = this.category,
        account = this.account,
        transactionType = this.transactionType
    )
}

// Mengubah dari Model (Domain/Firestore) ke Entity (Room)
fun TransactionModel.toEntity(): Transaction {
    return Transaction(
        // ID dari TransactionModel (String) sekarang cocok dengan ID di Entity (String)
        id = this.id,
        // userId dari TransactionModel (String?) cocok dengan userId di Entity (String?)
        userId = this.userId,
        title = this.title,
        date = this.date,
        amount = this.amount,
        category = this.category,
        account = this.account,
        transactionType = this.transactionType
    )
}
