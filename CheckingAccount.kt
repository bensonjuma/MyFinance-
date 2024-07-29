package com.ecom.myfinances.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checking_account_table")
data class CheckingAccount(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val accountNumber: String,
    val currentBalance: Double
)