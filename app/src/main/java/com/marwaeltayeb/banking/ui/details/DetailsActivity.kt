package com.marwaeltayeb.banking.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.databinding.ActivityDetailsBinding
import com.marwaeltayeb.banking.util.Const.Companion.CLIENT

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var currentClient: Client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (intent.getParcelableExtra(CLIENT) as Client?)?.let {
            currentClient = it
        }

        binding.txtName.text = currentClient.name
        binding.txtEmail.text = currentClient.email
        binding.txtPhoneNumber.text = currentClient.phoneNumber
        binding.txtBalance.text = currentClient.balance.toString()
    }
}