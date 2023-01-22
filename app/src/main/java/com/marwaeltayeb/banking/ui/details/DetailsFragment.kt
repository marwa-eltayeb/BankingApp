package com.marwaeltayeb.banking.ui.details

import android.content.res.Configuration
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.marwaeltayeb.banking.R
import com.marwaeltayeb.banking.data.model.Client
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marwaeltayeb.banking.databinding.FragmentDetailsBinding
import com.marwaeltayeb.banking.util.Const.Companion.AMOUNT
import com.marwaeltayeb.banking.util.Const.Companion.CLIENT
import com.marwaeltayeb.banking.util.Const.Companion.TRANSFEROR
import com.marwaeltayeb.banking.util.Const.Companion.TRANSFEROR_ID

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentClient: Client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentClient = arguments?.get(CLIENT) as Client
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        val alert: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val editAmount = EditText(requireContext())
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
                        val bundle = bundleOf(TRANSFEROR to currentClient.name, TRANSFEROR_ID to currentClient.client_id, AMOUNT to amount)
                        findNavController().navigate(R.id.action_detailsFragment_to_transferFragment, bundle)
                        dialog.dismiss()
                    }
                }
            })
    }
}