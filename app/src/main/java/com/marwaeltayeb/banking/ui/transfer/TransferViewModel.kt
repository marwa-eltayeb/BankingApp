package com.marwaeltayeb.banking.ui.transfer

import androidx.lifecycle.*
import com.marwaeltayeb.banking.data.BankRepository
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.data.model.Transaction
import kotlinx.coroutines.launch

class TransferViewModel(private val bankRepository: BankRepository) : ViewModel() {

    private var allClients: LiveData<List<Client>> = MutableLiveData()

    fun loadAllClients() {
        viewModelScope.launch {
            allClients = bankRepository.getClients()
        }
    }

    fun getAllClients(): LiveData<List<Client>> {
        return allClients
    }

    fun insertTransaction(transaction: Transaction){
        viewModelScope.launch {
            val idOfInsertedItem = bankRepository.insertTransaction(transaction)
            if(idOfInsertedItem > 0){
                bankRepository.decreaseMoney(transaction.amount, transaction.transferor)
                bankRepository.increaseMoney(transaction.amount, transaction.transferee)
            }
        }
    }
}

class TransferViewModelFactory(private val bankRepository: BankRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransferViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransferViewModel(bankRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}