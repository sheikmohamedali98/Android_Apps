package com.example.movieapppagination.pagination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapppagination.databinding.CardLayoutBinding
import com.example.movieapppagination.domin.MovieDomin
import com.example.movieapppagination.network.Result
import com.example.movieapppagination.network.MovieResponse

class MovieAdapter:PagingDataAdapter<Result,MovieAdapter.PagerViewHolder>(DiffCallBack)  {
    class PagerViewHolder(private val binding: CardLayoutBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind (movieDomin:Result){
            binding.movie = movieDomin
        }

    }
    companion object DiffCallBack:DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem:Result): Boolean  = oldItem == newItem

        override fun areContentsTheSame(oldItem:Result, newItem:Result): Boolean = oldItem == newItem

    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val layout  =  LayoutInflater.from(parent.context)
        val view = CardLayoutBinding.inflate(layout,parent,false)
        return  PagerViewHolder(view)
    }
}