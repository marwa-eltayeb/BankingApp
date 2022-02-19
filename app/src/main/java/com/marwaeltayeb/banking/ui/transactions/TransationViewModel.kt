package com.marwaeltayeb.banking.ui.transactions

import androidx.lifecycle.*
import com.marwaeltayeb.banking.data.BankRepository

class TransactionViewModel(private val bankRepository: BankRepository) : ViewModel() {

    val allTransactions = bankRepository.allTransactions
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