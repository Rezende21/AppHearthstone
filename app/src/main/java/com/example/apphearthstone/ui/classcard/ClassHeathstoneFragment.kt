package com.example.apphearthstone.ui.classcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.apphearthstone.databinding.FragmentClassHeathstoneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassHeathstoneFragment : Fragment() {

    private val binding by lazy { FragmentClassHeathstoneBinding.inflate(layoutInflater) }
    private lateinit var cardClass : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getButtonofClass()
        return binding.root
    }

    private fun getButtonofClass() {
        binding.btNeutral.setOnClickListener{
            cardClass = "Neutral"
            getClassCard()
        }
        binding.btMage.setOnClickListener {
            cardClass = "Mage"
            getClassCard()
        }
        binding.btWarlock.setOnClickListener {
            cardClass = "Warlock"
            getClassCard()
        }
        binding.btDemonHunter.setOnClickListener {
            cardClass = "Hunter"
            getClassCard()
        }
        binding.btDruid.setOnClickListener {
            cardClass = "Druid"
            getClassCard()
        }
        binding.btWarrior.setOnClickListener {
            cardClass = "Warrior"
            getClassCard()
        }
        binding.btHunter.setOnClickListener {
            cardClass = "Hunter"
            getClassCard()
        }
        binding.btRogue.setOnClickListener {
            cardClass = "Priest"
            getClassCard()
        }
        binding.btPaladin.setOnClickListener {
            cardClass = "Paladin"
            getClassCard()
        }
        binding.btPriest.setOnClickListener {
            cardClass = "Shaman"
            getClassCard()
        }
        binding.btShaman.setOnClickListener {
            cardClass = "Dream"
            getClassCard()
        }
    }

    private fun getClassCard() {
        findNavController().navigate(ClassHeathstoneFragmentDirections.actionClassHeathstoneFragmentToListCardsFragment2(cardClass))
    }
}