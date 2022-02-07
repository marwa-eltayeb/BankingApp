package com.marwaeltayeb.banking

import androidx.lifecycle.LiveData

class BankRepository (private val bankDao: BankDao) {

    fun getClients(): LiveData<List<Client>> {
        return bankDao.getClients()
    }

}