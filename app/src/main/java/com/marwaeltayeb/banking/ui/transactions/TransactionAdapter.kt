package com.marwaeltayeb.banking.ui.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.banking.R
import com.marwaeltayeb.banking.data.model.Transaction
import com.marwaeltayeb.banking.databinding.ItemTransactionListBinding
import com.marwaeltayeb.banking.util.formatDate

class TransactionAdapter : ListAdapter<Transaction, TransactionAdapter.TransactionViewHolder>(TRANSACTIONS_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val currentTransaction = getItem(position)
        holder.bind(currentTransaction)
    }

    class TransactionViewHolder(var binding: ItemTransactionListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.txtTransferor.text = transaction.transferor
            binding.txtTransferee.text = transaction.transferee
            val formattedDate: String = formatDate(transaction.date)
            binding.txtDate.text = formattedDate
            val formattedAmount: String = itemView.rootView.context.getString(R.string.amount, transaction.amount)
            binding.txtAmount.text = formattedAmount
        }
    }
}

private val TRANSACTIONS_COMPARATOR = object : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.transaction_id == newItem.transaction_id
    }
}