package com.marwaeltayeb.banking.ui.transfer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.marwaeltayeb.banking.R

class TransferActivity : AppCompatActivity() {

    private var amount: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)

        intent.getDoubleExtra("amount", 0.0).let {
            amount = it
        }

        Toast.makeText(this, amount.toString(), Toast.LENGTH_SHORT).show()

    }
}