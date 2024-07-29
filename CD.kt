package com.ecom.myfinances.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cd_table")
data class CD(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val accountNumber: String,
    val initialBalance: Double,
    val currentBalance: Double,
    val interestRate: Double
)