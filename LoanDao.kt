package com.ecom.myfinances.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ecom.myfinances.model.Loan

@Dao
interface LoanDao {
    @Insert
    suspend fun insert(loan: Loan)

    @Query("DELETE FROM loan_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM loan_table")
    fun getAllLoans(): LiveData<List<Loan>>

}