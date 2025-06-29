package com.example.hematin.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hematin.data.local.DateConverter
import com.example.hematin.data.local.database.dao.TransactionDao
import com.example.hematin.data.local.database.entity.Transaction

@Database(entities = [Transaction::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}