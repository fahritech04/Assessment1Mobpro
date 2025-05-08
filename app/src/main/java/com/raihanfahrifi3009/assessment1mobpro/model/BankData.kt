package com.raihanfahrifi3009.assessment1mobpro.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bankdata")
data class BankData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val namabank: String,
    val catatan: String,
    val tanggal: String
)
