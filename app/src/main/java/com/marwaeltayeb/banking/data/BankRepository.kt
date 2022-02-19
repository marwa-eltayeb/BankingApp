package com.marwaeltayeb.banking.data

import androidx.lifecycle.LiveData
import com.marwaeltayeb.banking.data.db.BankDao
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.data.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BankRepository(private val bankDao: BankDao) {

    fun getClients(): LiveData<List<Client>> {
        return bankDao.getClients()
    }

    suspend fun insertTransaction(transaction: Transaction): Result<Long> {
        return try {
            Result.success(bankDao.insertTransaction(transaction))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun decreaseMoney(amount: Double, transferor: String): Result<Int> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.success(bankDao.decreaseMoney(amount, transferor))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun increaseMoney(amount: Double, transferee: String): Result<Int> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.success(bankDao.increaseMoney(amount, transferee))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    fun getTransactions(): LiveData<List<Transaction>> {
        return bankDao.getTransactions()
    }
}


