package com.example.testapp.data.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.api.response.DataItem
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.example.testapp.databinding.ItemListUsernameBinding
import com.example.testapp.ui.secondscreen.SecondPageActivity

class UsernameAdapter(private val onItemClicked: (String) -> Unit) :
    PagingDataAdapter<DataItem, UsernameAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListUsernameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        data?.let { holder.bind(it) }

        holder.itemView.setOnClickListener {
            val firstName = data?.firstName
            val lastName = data?.lastName
            val fullName = "$firstName $lastName"
            onItemClicked(fullName)
        }
    }

    class MyViewHolder(private val binding: ItemListUsernameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            val firstName = data.firstName
            val lastName = data.lastName
            val fullName = "$firstName $lastName"
            binding.tvTipsTitle.text = fullName
            binding.tvDescription.text = data.email
            Glide.with(binding.ivTips).load(data.avatar).into(binding.ivTips)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}