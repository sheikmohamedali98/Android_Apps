package com.example.camerax2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.camerax2.databinding.ListImageBinding
import java.io.File

class GalleryAdapter(val fileArray:Array<File>): RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    class ViewHolder (val binding:ListImageBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(file: File){
            Glide.with(binding.root).load(file).into(binding.localImg)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ListImageBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fileArray[position])
    }

    override fun getItemCount(): Int= fileArray.size

}