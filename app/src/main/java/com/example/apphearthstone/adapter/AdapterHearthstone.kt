package com.example.apphearthstone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.apphearthstone.databinding.RowRecycleBinding
import com.example.apphearthstone.data.remote.HearthstoneModel

class AdapterHearthstone(private val onclick : (HearthstoneModel) -> Unit) :
    ListAdapter<HearthstoneModel, AdapterHearthstone.MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowRecycleBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val CardItem = getItem(position)
        holder.itemView.setOnClickListener {
            onclick(CardItem)
        }
        holder.bind(CardItem)
    }

    class MyViewHolder(private val binding: RowRecycleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cards : HearthstoneModel) {
            binding.txtcarname.text = cards.name
        }
    }
    class DiffCallback : DiffUtil.ItemCallback<HearthstoneModel>(){
        override fun areItemsTheSame(oldItem: HearthstoneModel, newItem: HearthstoneModel): Boolean = oldItem.cardId == newItem.cardId
        override fun areContentsTheSame(oldItem: HearthstoneModel, newItem: HearthstoneModel): Boolean = oldItem == newItem
    }
}


