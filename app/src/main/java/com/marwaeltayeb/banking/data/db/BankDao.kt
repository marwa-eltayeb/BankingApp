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
    suspend fun decreaseMoney(amount : Double, transferor: String):Int

    @Query("Update client_table set balance = balance + :amount where name = :transferee")
    suspend fun increaseMoney(amount : Double, transferee: String): Int

    @androidx.room.Transaction
    suspend fun insertAndUpdateTransaction(transaction: Transaction) : Int{
        val idOfInsertedItem = insertTransaction(transaction)
        if (idOfInsertedItem > 0){
            val decreaseResult = decreaseMoney(transaction.amount, transaction.transferor)
            val increaseResult = increaseMoney(transaction.amount, transaction.transferee)
            if((decreaseResult > 0) && (increaseResult> 0)){
                return 1
            }
        }
        return -1
    }

    @Query("SELECT * FROM transaction_table")
    fun getTransactions(): LiveData<List<Transaction>>
}