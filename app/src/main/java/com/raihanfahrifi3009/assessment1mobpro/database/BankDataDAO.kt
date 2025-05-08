package com.raihanfahrifi3009.assessment1mobpro.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.raihanfahrifi3009.assessment1mobpro.model.BankData
import kotlinx.coroutines.flow.Flow

@Dao
interface BankDataDAO {
    @Insert
    suspend fun insert(bankData: BankData)

    @Update
    suspend fun update(bankData: BankData)

    @Query("SELECT * FROM bankdata ORDER BY tanggal DESC")
    fun getDataBank(): Flow<List<BankData>>

    @Query("SELECT * FROM bankdata WHERE id = :id")
    suspend fun getBankDataById(id: Long): BankData?
}