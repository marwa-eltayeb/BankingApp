package com.marwaeltayeb.banking.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwaeltayeb.banking.R
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.data.model.Transaction
import com.marwaeltayeb.banking.databinding.FragmentTransferBinding
import com.marwaeltayeb.banking.ui.clients.ClientAdapter
import com.marwaeltayeb.banking.util.Const.Companion.AMOUNT
import com.marwaeltayeb.banking.util.Const.Companion.TRANSFEROR
import com.marwaeltayeb.banking.util.Const.Companion.TRANSFEROR_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransferFragment : Fragment(), ClientAdapter.OnItemClickListener {

    private var _binding: FragmentTransferBinding? = null
    private val binding get() = _binding!!

    private var amount: Double = 0.0
    private var transferor: String = ""
    private var transferorID: Int = 0

    private val transferViewModel: TransferViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        transferor = arguments?.get(TRANSFEROR) as String
        transferorID = arguments?.get(TRANSFEROR_ID) as Int
        amount = arguments?.get(AMOUNT) as Double
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTransferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val clientAdapter = ClientAdapter()
        binding.recReceiversList.adapter = clientAdapter
        binding.recReceiversList.layoutManager = LinearLayoutManager(requireContext())
        clientAdapter.setOnItemClickListener(this)

        transferViewModel.getAllClients().observe(viewLifecycleOwner) { clients ->
            val list = clients.toMutableList()
            list.removeAt(transferorID - 1)
            clientAdapter.submitList(list)
        }

        transferViewModel.completeTask.observe(viewLifecycleOwner) { isTaskCompleted ->
            if (isTaskCompleted == true) {
                Toast.makeText(requireContext(), "Transaction Successfully Completed", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_transferFragment_to_clientFragment)
            }
        }
    }

    override fun onItemClick(client: Client) {
        val transaction = Transaction(System.currentTimeMillis(), transferor, client.name, amount)
        transferViewModel.insertTransaction(transaction)
    }
}