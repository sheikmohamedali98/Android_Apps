package com.example.contact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contact.R
import com.example.contact.database.Contact
import com.example.contact.databinding.CardContactBinding

class ContactAdapter:ListAdapter<Contact, ContactAdapter.ViewHolder>(DiffCallBack) {
    class ViewHolder(private val binding: CardContactBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact){
            binding.contact = contact
        }

    }
    object DiffCallBack: DiffUtil.ItemCallback<Contact>(){
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CardContactBinding.inflate(LayoutInflater.from(parent.context))
       return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val contact = getItem(position)
        holder.bind(contact)
    }

}