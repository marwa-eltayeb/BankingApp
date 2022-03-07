package com.marwaeltayeb.banking.ui.details

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.marwaeltayeb.banking.R
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.databinding.ActivityDetailsBinding
import com.marwaeltayeb.banking.ui.transfer.TransferActivity
import com.marwaeltayeb.banking.util.Const.Companion.CLIENT
import com.marwaeltayeb.banking.util.Const.Companion.AMOUNT
import com.marwaeltayeb.banking.util.Const.Companion.TRANSFEROR
import android.view.View
import com.marwaeltayeb.banking.util.Const.Companion.TRANSFEROR_ID

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
        val formattedBalance: String = getString(R.string.amount, currentClient.balance)
        binding.txtBalance.text = formattedBalance

        binding.btnTransferMoney.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog() {
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        val editAmount = EditText(this)
        editAmount.inputType = InputType.TYPE_CLASS_NUMBER
        editAmount.setRawInputType(Configuration.KEYBOARD_12KEY)
        editAmount.hint = "Amount"
        editAmount.setSingleLine()
        alert.setTitle("Enter Amount")
        alert.setView(editAmount)

        alert.setPositiveButton("OK") { dialog, which -> }
        alert.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }

        val dialog = alert.create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val amountString: String = editAmount.text.toString().trim()
                    if (amountString.isEmpty()) {
                        editAmount.error = "Amount can not be empty"
                        return
                    } else {
                        val amount: Double = amountString.toDouble()
                        if (amount > currentClient.balance){
                            editAmount.error = "You do not have enough balance"
                            return
                        }
                        intent = Intent(applicationContext, TransferActivity::class.java)
                        intent.putExtra(TRANSFEROR, currentClient.name)
                        intent.putExtra(TRANSFEROR_ID, currentClient.client_id)
                        intent.putExtra(AMOUNT, amount)
                        startActivity(intent)
                        dialog.dismiss()
                    }
                }
            })
    }
}