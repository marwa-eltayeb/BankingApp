package com.marwaeltayeb.banking.ui.transfer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.banking.BankApplication
import com.marwaeltayeb.banking.R
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.data.model.Transaction
import com.marwaeltayeb.banking.ui.clients.ClientAdapter
import com.marwaeltayeb.banking.ui.clients.MainActivity
import com.marwaeltayeb.banking.util.Const.Companion.AMOUNT
import com.marwaeltayeb.banking.util.Const.Companion.TRANSFEROR
import com.marwaeltayeb.banking.util.Const.Companion.TRANSFEROR_ID

class TransferActivity : AppCompatActivity() , ClientAdapter.OnItemClickListener{

    private var amount: Double = 0.0
    private var transferor: String = ""
    private var transferorID: Int = 0

    private val transferViewModel: TransferViewModel by viewModels {
        TransferViewModelFactory((application as BankApplication).bankRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)

        intent.getDoubleExtra(AMOUNT, 0.0).let {
            amount = it
        }

        intent.getStringExtra(TRANSFEROR)?.let {
            transferor = it
        }

        intent.getIntExtra(TRANSFEROR_ID, 0).let {
            transferorID = it
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recReceiversList)
        val clientAdapter = ClientAdapter()
        recyclerView.adapter = clientAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        clientAdapter.setOnItemClickListener(this)

        transferViewModel.loadAllClients()
        transferViewModel.getAllClients().observe(this) { clients ->
            clients.let {
                val list = clients.toMutableList()
                list.removeAt(transferorID - 1)
                clientAdapter.submitList(list)
            }
        }
    }

    override fun onItemClick(client: Client) {
        val transaction = Transaction(System.currentTimeMillis(), transferor, client.name, amount)
        transferViewModel.insertTransaction(transaction)
        Toast.makeText(this, "Transaction Successfully Completed", Toast.LENGTH_SHORT).show()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}