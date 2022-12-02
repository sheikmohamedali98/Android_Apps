package com.example.newsapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.CardLayoutNewsBinding
import com.example.newsapp.domain.DomainData

class VideoAdapter(private  val onClickListener: OnClickListener) : ListAdapter<DomainData, VideoAdapter.ViewHolder>(DiffcallBack) {

    private var shareClickListener:((url:String)->Unit)? = null

    class ViewHolder(private var binding: CardLayoutNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(domainData: DomainData,shareClickListener:((url:String)->Unit)?) {
            binding.viewModel = domainData

            binding.share.setOnClickListener {
                    shareClickListener?.invoke(domainData.url?:"")
            }

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
        return ViewHolder(CardLayoutNewsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(data)
        }

        holder.bind(data,shareClickListener)
    }

//    fun setOnclickListener(clickListener:((domianData:DomainData)->Unit)){
//        this.clickListener = clickListener
//    }

    fun setShareClickListener(shareClickListener: ((url: String) -> Unit)?){
        this.shareClickListener = shareClickListener
    }
class OnClickListener(val clickListener:(dominData:DomainData)->Unit){
    fun onClick(domianData: DomainData) = clickListener(domianData)
}
}
