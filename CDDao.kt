package com.ecom.myfinances.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ecom.myfinances.model.CD

@Dao
interface CDDao {
    @Insert
    suspend fun insert(cd: CD)

    @Query("DELETE FROM cd_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM cd_table")
    fun getAllCDs(): LiveData<List<CD>>

}