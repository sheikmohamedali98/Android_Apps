package com.example.contact.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.example.contact.R
import com.example.contact.database.Contact
import com.example.contact.databinding.CardContactBinding

class ContactAdapter(val clickListener: ClickListener) :
    ListAdapter<Contact, ContactAdapter.ViewHolder>(DiffCallBack) {


    object DiffCallBack : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CardContactBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }

    class ViewHolder(
        private val binding: CardContactBinding,
        private val clickListener: ClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {



        fun bind(contactData: Contact) {
            binding.apply {
                contact = contactData
                if (contactData.imageUrl == "") {
//                    contactImageId.visibility = View.VISIBLE
//                    contactImageId.text = contactData.firstname[0].toString().uppercase()
                    val generator = ColorGenerator.MATERIAL
                    val color: Int = generator.randomColor
                    val textColor = R.color.black

                    val textDrawable = TextDrawable.Builder()
                        .setWidth(100)
                        .setBold()
                        .setTextColor(textColor.toInt())
                        .setHeight(100)
                        .setColor(color)
                        .setText(contactData.firstname.substring(0, 1))
                        .build()
                    personImage.setImageDrawable(textDrawable)
                }else{
                    personImage.setImageURI(contactData.imageUrl.toUri())

                }
                cardContact.setOnClickListener {
                    clickListener.onCardClickListener(contactData)
                }
            }
        }
    }


    interface ClickListener {
        fun onCardClickListener(contact: Contact)
    }
}
