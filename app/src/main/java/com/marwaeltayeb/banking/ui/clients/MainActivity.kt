package com.marwaeltayeb.banking.ui.clients

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.banking.BankApplication
import com.marwaeltayeb.banking.R

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as BankApplication).bankRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recClientsList)
        val clientAdapter = ClientAdapter()
        recyclerView.adapter = clientAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainViewModel.loadAllClients()
        mainViewModel.getAllClients().observe(this) { clients ->
            clients.let { clientAdapter.submitList(it) }
        }
    }
}
