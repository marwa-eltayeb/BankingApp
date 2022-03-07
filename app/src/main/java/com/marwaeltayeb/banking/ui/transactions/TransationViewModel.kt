package com.marwaeltayeb.banking.ui.transactions

import androidx.lifecycle.*
import com.marwaeltayeb.banking.data.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val bankRepository: BankRepository) : ViewModel() {

    val allTransactions = bankRepository.allTransactions
}

