package com.marwaeltayeb.banking.ui.clients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.banking.R
import com.marwaeltayeb.banking.data.model.Client

class ClientAdapter : ListAdapter<Client, ClientAdapter.ClientViewHolder>(Clients_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_client_list, parent, false)
        return ClientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val currentClient = getItem(position)
        holder.bind(currentClient)
    }

    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtName: TextView = itemView.findViewById(R.id.txtName)
        private val txtEmail: TextView = itemView.findViewById(R.id.txtEmail)
        private val txtBalance: TextView = itemView.findViewById(R.id.txtBalance)

        fun bind(client: Client) {
            txtName.text = client.name
            txtEmail.text = client.email
            txtBalance.text = client.balance.toString()
        }
    }
}

private val Clients_COMPARATOR = object : DiffUtil.ItemCallback<Client>() {
    override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
        return oldItem.name == newItem.name
    }
}