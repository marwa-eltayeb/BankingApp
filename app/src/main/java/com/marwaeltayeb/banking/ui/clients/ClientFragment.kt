package com.marwaeltayeb.banking.ui.clients

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwaeltayeb.banking.R
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.databinding.FragmentClientBinding
import com.marwaeltayeb.banking.util.Const.Companion.CLIENT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientFragment : Fragment(), ClientAdapter.OnItemClickListener {

    private var _binding: FragmentClientBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var clientAdapter:ClientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentClientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clientAdapter = ClientAdapter()
        binding.recClientsList.adapter = clientAdapter
        binding.recClientsList.layoutManager = LinearLayoutManager(context)
        clientAdapter.setOnItemClickListener(this)

        mainViewModel.getAllClients().observe(viewLifecycleOwner) { clients ->
            clientAdapter.submitList(clients)
        }
    }

    override fun onItemClick(client: Client) {
        val bundle = bundleOf(CLIENT to client)
        findNavController().navigate(R.id.action_clientFragment_to_detailsFragment, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history_action -> {
                findNavController().navigate(R.id.action_clientFragment_to_transactionFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
