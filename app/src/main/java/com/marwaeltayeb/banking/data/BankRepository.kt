package com.marwaeltayeb.banking.data

import androidx.lifecycle.LiveData
import com.marwaeltayeb.banking.data.db.BankDao
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.data.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BankRepository(private val bankDao: BankDao) {

    val allClients: LiveData<List<Client>> = bankDao.getClients()
    val allTransactions: LiveData<List<Transaction>> = bankDao.getTransactions()

    suspend fun insertTransactionAndUpdate(transaction: Transaction): Result<Int>{
        return try {
            Result.success(bankDao.insertAndUpdateTransaction(transaction))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


