package com.marwaeltayeb.banking.ui.clients

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.banking.BankApplication
import com.marwaeltayeb.banking.R
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.ui.details.DetailsActivity
import com.marwaeltayeb.banking.util.Const.Companion.CLIENT

class MainActivity : AppCompatActivity(), ClientAdapter.OnItemClickListener {

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
        clientAdapter.setOnItemClickListener(this)

        mainViewModel.loadAllClients()
        mainViewModel.getAllClients().observe(this) { clients ->
            clients.let { clientAdapter.submitList(it) }
        }
    }

    override fun onItemClick(client: Client) {
        intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(CLIENT, client)
        startActivity(intent)
    }
}
