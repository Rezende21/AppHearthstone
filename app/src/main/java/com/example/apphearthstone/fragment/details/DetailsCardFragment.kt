package com.example.apphearthstone.fragment.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.apphearthstone.databinding.FragmentDetailsCardBinding
import com.example.apphearthstone.state.HearthstoneState

class DetailsCardFragment : Fragment() {

    private val binding by lazy { FragmentDetailsCardBinding.inflate(layoutInflater) }
    private val viewModel : ViewModelDetailsCard by viewModels()
    private val args : DetailsCardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cardId = args.cardid.cardId
        serchSingleCard(cardId)
        return binding.root
    }

    private fun serchSingleCard(cardId: String) {
        viewModel.serchSingleCard(cardId)
        viewModel.result.observe(viewLifecycleOwner) {
            when(it) {
                is HearthstoneState.Success -> {
                    binding.txtNome.text = it.data!![0].name
                    binding.txtDescricao.text = it.data[0].text
                    binding.txtataque.text = it.data[0].attack.toString()
                    binding.txtcusto.text = it.data[0].cost.toString()
                    binding.txtfaccao.text = it.data[0].faction
                    binding.txtipo.text = it.data[0].type
                    binding.txtraridade.text = it.data[0].rarity
                    binding.txtvida.text = it.data[0].health.toString()
                    binding.loadingBar.hide()
                }
                is HearthstoneState.Loading -> {
                    binding.loadingBar.show()
                }
                is HearthstoneState.Error -> {
                    Toast.makeText(requireContext(), "Nâo foi possivel encontra Carta", Toast.LENGTH_LONG).show()
                    Log.e("DetailsCard", it.message.toString())
                    activity?.onBackPressed()
                }
            }
        }
    }
}