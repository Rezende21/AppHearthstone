package com.example.apphearthstone.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apphearthstone.adapter.AdapterHearthstone
import com.example.apphearthstone.databinding.FragmentListCardsBinding
import com.example.apphearthstone.data.remote.HearthstoneModel
import com.example.apphearthstone.state.HearthstoneState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCardsFragment : Fragment() {

    private val binding by lazy { FragmentListCardsBinding.inflate(layoutInflater) }
    private val adapter by lazy { AdapterHearthstone {
        onDetailsCard(it)
    }}
    private val viewModelHearthstone by viewModels<ViewModelHearthstone>()
    private val args : ListCardsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cardView = args.cardview
        initViewWithCards(cardView)
        return binding.root
    }

    private fun initViewWithCards(cardView : String) {
        binding.loadingProgress.show()
        binding.recycleView.adapter = adapter
        viewModelHearthstone.getCardFromClass(cardView)
        viewModelHearthstone.results.observe(viewLifecycleOwner) {
            when(it) {
                is HearthstoneState.Success -> {
                    adapter.submitList(it.data)
                    binding.loadingProgress.hide()
                }
                is HearthstoneState.Loading -> {
                    binding.loadingProgress.show()
                }
                is HearthstoneState.Error -> {
                    binding.loadingProgress.hide()
                    Toast.makeText(requireContext(), it.message , Toast.LENGTH_LONG).show()
                    Log.e("ListCard", it.message.toString())
                }
            }
        }
    }

    private fun onDetailsCard(it: HearthstoneModel) {
        findNavController().navigate(ListCardsFragmentDirections.actionListCardsFragmentToDetailsCardFragment(it))
    }
}