package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.CardLayoutBinding
import com.example.movieapp.domin.MovieDomine
import com.example.movieapp.network.MovieResponse

class MovieAdapter(private  val onClickListener: OnClickListener):ListAdapter<MovieDomine,MovieAdapter.ItemViewHolder>(DifferItemCallback()) {

    class ItemViewHolder(private val binding: CardLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(movieDomine:MovieDomine){
            binding.movie = movieDomine
        }

    }

    class DifferItemCallback:DiffUtil.ItemCallback<MovieDomine>(){
        override fun areItemsTheSame(oldItem: MovieDomine, newItem: MovieDomine): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieDomine, newItem: MovieDomine): Boolean = oldItem == newItem

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movieItem)
        }
        if (movieItem != null) {
            holder.bind(movieItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = CardLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ItemViewHolder(view)
    }

    class OnClickListener(val clickListener:(dominData:MovieDomine)->Unit){
        fun onClick(domianData: MovieDomine) = clickListener(domianData)
    }

}