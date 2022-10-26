package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.CardLayoutBinding
import com.example.newsapp.domain.DomainData
import com.example.newsapp.network.Data

class VideoAdapter : ListAdapter<DomainData, VideoAdapter.ViewHolder>(DiffcallBack) {
    class ViewHolder(private var binding: CardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(domainData: DomainData) {
            binding.viewModel = domainData
//            binding.imageView = data.imageUrl.toString()
//            binding.dateView = data.date
        }
    }

    object DiffcallBack : DiffUtil.ItemCallback<DomainData>() {
        override fun areItemsTheSame(oldItem: DomainData, newItem: DomainData): Boolean {
            return (oldItem.id).equals(newItem.id)
        }

        override fun areContentsTheSame(oldItem: DomainData, newItem: DomainData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}