package com.marwaeltayeb.banking.ui.transactions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwaeltayeb.banking.databinding.ActivityTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionBinding

    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transactionAdapter = TransactionAdapter()
        binding.recTransactionsList.adapter = transactionAdapter
        binding.recTransactionsList.layoutManager = LinearLayoutManager(this)

        transactionViewModel.allTransactions.observe(this) { transactions ->
            transactions.let { transactionAdapter.submitList(it) }
        }
    }
}