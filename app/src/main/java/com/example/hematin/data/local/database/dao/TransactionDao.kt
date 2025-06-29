package com.example.hematin.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hematin.data.local.database.entity.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE userId = :userId ORDER BY date DESC")
    fun getAllByUserId(userId: String): Flow<List<Transaction>>

    // PERBAIKAN: Ubah tipe parameter id menjadi String
    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getById(id: String): Flow<Transaction?>

    @Query("DELETE FROM transactions WHERE userId = :userId")
    suspend fun deleteAllByUserId(userId: String)
}