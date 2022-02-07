package com.marwaeltayeb.banking.data

import androidx.lifecycle.LiveData
import com.marwaeltayeb.banking.data.db.BankDao
import com.marwaeltayeb.banking.data.model.Client

class BankRepository (private val bankDao: BankDao) {

    fun getClients(): LiveData<List<Client>> {
        return bankDao.getClients()
    }

}