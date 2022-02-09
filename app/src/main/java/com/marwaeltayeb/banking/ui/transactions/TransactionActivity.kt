package com.marwaeltayeb.banking.ui.transactions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.banking.BankApplication
import com.marwaeltayeb.banking.R

class TransactionActivity : AppCompatActivity() {

    private val transactionViewModel: TransactionViewModel by viewModels {
        TransactionViewModelFactory((application as BankApplication).bankRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        val recyclerView = findViewById<RecyclerView>(R.id.recTransactionsList)
        val transactionAdapter = TransactionAdapter()
        recyclerView.adapter = transactionAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        transactionViewModel.loadAllTransactions()
        transactionViewModel.getAllTransactions().observe(this) { transactions ->
            transactions.let { transactionAdapter.submitList(it) }
        }
    }
}