package com.ecom.myfinances.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ecom.myfinances.model.CheckingAccount

@Dao
interface CheckingAccountDao {
    @Insert
    suspend fun insert(checkingAccount: CheckingAccount)

    @Query("DELETE FROM checking_account_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM checking_account_table")
    fun getAllCheckingAccounts(): LiveData<List<CheckingAccount>>

}