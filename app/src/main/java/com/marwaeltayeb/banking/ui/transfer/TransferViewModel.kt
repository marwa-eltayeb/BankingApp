package com.marwaeltayeb.banking.ui.transfer

import androidx.lifecycle.*
import com.marwaeltayeb.banking.data.BankRepository
import com.marwaeltayeb.banking.data.model.Transaction
import kotlinx.coroutines.launch

class TransferViewModel(private val bankRepository: BankRepository) : ViewModel() {

    private val _completeTask = MutableLiveData<Boolean>()
    val completeTask = _completeTask

    val allClients = bankRepository.allClients

    fun insertTransaction(transaction: Transaction){
        viewModelScope.launch {
            val insertionResult = bankRepository.insertTransactionAndUpdate(transaction)
            if((insertionResult.getOrDefault(-1) > 0)){
                _completeTask.value = true
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