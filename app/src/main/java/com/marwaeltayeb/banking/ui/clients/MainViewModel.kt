package com.marwaeltayeb.banking.ui.clients

import androidx.lifecycle.*
import com.marwaeltayeb.banking.data.BankRepository
import com.marwaeltayeb.banking.data.model.Client
import kotlinx.coroutines.launch

class MainViewModel(private val bankRepository: BankRepository) : ViewModel() {

    private var allClients: LiveData<List<Client>> = MutableLiveData()

    fun loadAllClients() {
        viewModelScope.launch {
            allClients = bankRepository.getClients()
        }
    }

    fun getAllClients(): LiveData<List<Client>> {
        return allClients
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