package com.ecom.myfinances.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loan_table")
data class Loan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val accountNumber: String,
    val initialBalance: Double,
    val currentBalance: Double,
    val paymentAmount: Double,
    val interestRate: Double
)