package com.ecom.myfinances.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ecom.myfinances.model.CD
import com.ecom.myfinances.model.Loan
import com.ecom.myfinances.model.CheckingAccount

@Database(entities = [CD::class, Loan::class, CheckingAccount::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cdDao(): CDDao
    abstract fun loanDao(): LoanDao
    abstract fun checkingAccountDao(): CheckingAccountDao
}