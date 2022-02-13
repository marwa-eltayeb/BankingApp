package com.marwaeltayeb.banking.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marwaeltayeb.banking.databinding.ActivityHomeScreenBinding
import com.marwaeltayeb.banking.ui.clients.MainActivity

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBank.alpha = 0f
        binding.imgBank.animate().setDuration(1500).alpha(1f).withEndAction{
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}