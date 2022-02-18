package com.marwaeltayeb.banking.ui.clients

import androidx.lifecycle.*
import com.marwaeltayeb.banking.data.BankRepository
import com.marwaeltayeb.banking.data.model.Client

class MainViewModel(private val bankRepository: BankRepository) : ViewModel() {

    fun getAllClients(): LiveData<List<Client>> {
        return bankRepository.getClients()
    }
}

class MainViewModelFactory(private val bankRepository: BankRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(bankRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}