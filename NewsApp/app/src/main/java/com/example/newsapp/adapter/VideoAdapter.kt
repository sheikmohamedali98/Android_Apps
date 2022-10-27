package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.CardLayoutBinding
import com.example.newsapp.domain.DomainData

class VideoAdapter(private  val onClickListener: OnClickListener) : ListAdapter<DomainData, VideoAdapter.ViewHolder>(DiffcallBack) {

//    private var clickListener:((domianData:DomainData)->Unit)? = null

    class ViewHolder(private var binding: CardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(domainData: DomainData) {
            binding.viewModel = domainData
//            binding.cardView.setOnClickListener{
////                WebViewViewModel.url = domainData.url.toString()
//
////                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment)
//            }
//            binding.imageView = data.imageUrl.toString()
//            binding.dateView = data.date
//            clickListener?.let {
//                binding.root.setOnClickListener {
//                    clickListener.invoke(domainData)
//                }
//            }
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
        holder.itemView.setOnClickListener {
            onClickListener.onClick(data)
        }
        holder.bind(data)
    }

//    fun setOnclickListener(clickListener:((domianData:DomainData)->Unit)){
//        this.clickListener = clickListener
//    }
class OnClickListener(val clickListener:(dominData:DomainData)->Unit){
    fun onClick(domianData: DomainData) = clickListener(domianData)
}
}
