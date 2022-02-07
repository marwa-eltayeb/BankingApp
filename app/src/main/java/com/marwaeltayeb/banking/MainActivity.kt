package com.marwaeltayeb.banking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recClientsList)
        val clientAdapter = ClientAdapter()
        recyclerView.adapter = clientAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val numbersList: MutableList<Client> = mutableListOf(Client("John", "john@gamil.com", "34323342", 4000.00), Client("Emily", "emily@gamil.com", "98347844", 2000.00), Client("Chris", "chris@gamil.com", "87344375", 9000.00))
        clientAdapter.submitList(numbersList)
    }
}
