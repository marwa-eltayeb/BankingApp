package com.marwaeltayeb.banking.ui.transactions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwaeltayeb.banking.BankApplication
import com.marwaeltayeb.banking.databinding.ActivityTransactionBinding

class TransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionBinding

    private val transactionViewModel: TransactionViewModel by viewModels {
        TransactionViewModelFactory((application as BankApplication).bankRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transactionAdapter = TransactionAdapter()
        binding.recTransactionsList.adapter = transactionAdapter
        binding.recTransactionsList.layoutManager = LinearLayoutManager(this)

        transactionViewModel.loadAllTransactions()
        transactionViewModel.getAllTransactions().observe(this) { transactions ->
            transactions.let { transactionAdapter.submitList(it) }
        }
    }
}