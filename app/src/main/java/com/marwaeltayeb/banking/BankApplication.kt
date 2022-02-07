package com.marwaeltayeb.banking

import android.app.Application
import com.marwaeltayeb.banking.data.BankRepository
import com.marwaeltayeb.banking.data.db.BankDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BankApplication : Application(){

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { BankDatabase.getDatabase(this, applicationScope) }
    val bankRepository by lazy { BankRepository(database.bankDao()) }
}