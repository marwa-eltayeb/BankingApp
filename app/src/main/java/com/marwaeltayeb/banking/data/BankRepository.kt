package com.marwaeltayeb.banking.data

import androidx.lifecycle.LiveData
import com.marwaeltayeb.banking.data.db.BankDao
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.data.model.Transaction

class BankRepository (private val bankDao: BankDao) {

    fun getClients(): LiveData<List<Client>> {
        return bankDao.getClients()
    }

    suspend fun insertTransaction(transaction: Transaction): Long {
        return bankDao.insertTransaction(transaction)
    }

    suspend fun decreaseMoney(amount : Double, transferor: String) {
        bankDao.decreaseMoney(amount, transferor)
    }

    suspend fun increaseMoney(amount : Double, transferee: String) {
        bankDao.increaseMoney(amount, transferee)
    }

    fun getTransactions(): LiveData<List<Transaction>> {
        return bankDao.getTransactions()
    }
}
