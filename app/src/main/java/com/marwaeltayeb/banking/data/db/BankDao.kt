package com.marwaeltayeb.banking.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.data.model.Transaction

@Dao
interface BankDao {

    @Query("SELECT * FROM client_table")
    fun getClients(): LiveData<List<Client>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClients(words : List<Client>)

    @Query("DELETE FROM client_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction): Long

    @Query("Update client_table set balance = balance - :amount where name = :transferor")
    suspend fun decreaseMoney(amount : Double, transferor: String)

    @Query("Update client_table set balance = balance + :amount where name = :transferee")
    suspend fun increaseMoney(amount : Double, transferee: String)

    @Query("SELECT * FROM transaction_table")
    fun getTransactions(): LiveData<List<Transaction>>
}