package com.marwaeltayeb.banking.ui.clients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.banking.R
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.databinding.ItemClientListBinding

class ClientAdapter : ListAdapter<Client, ClientAdapter.ClientViewHolder>(Clients_COMPARATOR) {

    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(client: Client)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val binding = ItemClientListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val currentClient = getItem(position)
        holder.bind(currentClient)

        if(::onItemClickListener.isInitialized){
            holder.itemView.setOnClickListener {
                onItemClickListener.onItemClick(currentClient)
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        onItemClickListener = listener
    }

    class ClientViewHolder(var binding: ItemClientListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(client: Client) {
            binding.txtName.text = client.name
            binding.txtEmail.text = client.email
            val formattedAmount: String = itemView.rootView.context.getString(R.string.amount, client.balance)
            binding.txtBalance.text = formattedAmount
        }
    }
}

private val Clients_COMPARATOR = object : DiffUtil.ItemCallback<Client>() {
    override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
        return oldItem.client_id == newItem.client_id
    }
}