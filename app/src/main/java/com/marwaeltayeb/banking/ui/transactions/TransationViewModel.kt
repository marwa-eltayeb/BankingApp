package com.marwaeltayeb.banking.ui.transactions

import androidx.lifecycle.*
import com.marwaeltayeb.banking.data.BankRepository
import com.marwaeltayeb.banking.data.model.Transaction
import kotlinx.coroutines.launch

class TransactionViewModel(private val bankRepository: BankRepository) : ViewModel() {

    private var allTransactions: LiveData<List<Transaction>> = MutableLiveData()

    fun loadAllTransactions() {
        viewModelScope.launch {
            allTransactions = bankRepository.getTransactions()
        }
    }

    fun getAllTransactions(): LiveData<List<Transaction>> {
        return allTransactions
    }
}

class TransactionViewModelFactory(private val bankRepository: BankRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransactionViewModel(bankRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}