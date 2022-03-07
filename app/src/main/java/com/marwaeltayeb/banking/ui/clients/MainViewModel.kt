package com.marwaeltayeb.banking.ui.clients

import androidx.lifecycle.*
import com.marwaeltayeb.banking.data.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val bankRepository: BankRepository) : ViewModel() {

    val allClients = bankRepository.allClients
}

