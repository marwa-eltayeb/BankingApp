package com.marwaeltayeb.banking.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwaeltayeb.banking.databinding.FragmentTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!

    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val transactionAdapter = TransactionAdapter()
        binding.recTransactionsList.adapter = transactionAdapter
        binding.recTransactionsList.layoutManager = LinearLayoutManager(requireContext())

        transactionViewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            transactions.let { transactionAdapter.submitList(it) }
        }
    }
}