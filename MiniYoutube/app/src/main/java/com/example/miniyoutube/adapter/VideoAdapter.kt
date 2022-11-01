package com.example.miniyoutube.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.miniyoutube.R
import com.example.miniyoutube.databinding.LinearLayoutBinding
import com.example.miniyoutube.domain.VideoModel

//
class VideoAdapter(private  val onClickListner: OnClickListner):ListAdapter<VideoModel,VideoAdapter.VideoViewHolder>(DiffCallBack) {

    class VideoViewHolder(private var binding: LinearLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun  bind(videoModel: VideoModel){
            binding.videoModel = videoModel
//            binding.executePendingBindings()
        }


    }

    companion object DiffCallBack:DiffUtil.ItemCallback<VideoModel>() {
        override fun areItemsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean = oldItem.id ==newItem.id

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.linear_layout,parent,false)
       val view = LinearLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return  VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {

        val videoModel = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListner.onClick(videoModel)
        }
        holder.bind(videoModel)
//        holder.itemView.setOnClickListener {
//            onClickListner.onClick(videoModel)
//        }
    }
    class OnClickListner(val clickListner:(videoModel:VideoModel)->Unit){
        fun onClick (videoModel: VideoModel) = clickListner(videoModel)
    }
}