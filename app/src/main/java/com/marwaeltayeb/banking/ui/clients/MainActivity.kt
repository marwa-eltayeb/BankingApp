package com.marwaeltayeb.banking.ui.clients

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwaeltayeb.banking.BankApplication
import com.marwaeltayeb.banking.R
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.databinding.ActivityMainBinding
import com.marwaeltayeb.banking.ui.details.DetailsActivity
import com.marwaeltayeb.banking.ui.transactions.TransactionActivity
import com.marwaeltayeb.banking.util.Const.Companion.CLIENT

class MainActivity : AppCompatActivity(), ClientAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as BankApplication).bankRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientAdapter = ClientAdapter()
        binding.recClientsList.adapter = clientAdapter
        binding.recClientsList.layoutManager = LinearLayoutManager(this)
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


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history_action -> {
                intent = Intent(this, TransactionActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
