package com.marwaeltayeb.banking.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marwaeltayeb.banking.data.model.Client

@Dao
interface BankDao {

    @Query("SELECT * FROM client_table")
    fun getClients(): LiveData<List<Client>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClient(client: Client)

    @Query("DELETE FROM client_table")
    suspend fun deleteAll()
}