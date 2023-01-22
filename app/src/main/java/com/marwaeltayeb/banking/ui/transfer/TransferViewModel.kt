package com.marwaeltayeb.banking.ui.transfer

import androidx.lifecycle.*
import com.marwaeltayeb.banking.data.BankRepository
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.data.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(private val bankRepository: BankRepository) : ViewModel() {

    private val _completeTask = MutableLiveData<Boolean>()
    val completeTask = _completeTask

    fun getAllClients():LiveData<List<Client>>{
        return bankRepository.getClients()
    }

    fun insertTransaction(transaction: Transaction){
        viewModelScope.launch {
            val insertionResult = bankRepository.insertTransactionAndUpdate(transaction)
            if((insertionResult.getOrDefault(-1) > 0)){
                _completeTask.value = true
            }
        }
    }
}
