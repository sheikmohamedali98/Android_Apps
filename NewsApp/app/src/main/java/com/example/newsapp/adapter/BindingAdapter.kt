package com.example.newsapp.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.network.Data

@BindingAdapter("imagUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(RequestOptions())
            .into(imageView)
    }
//    println("\n\n\n\n${imgUrl}\n")
}


@BindingAdapter("listNews")
fun bindRecycleView(recyclerView: RecyclerView,data:List<Data>?){
 val adapter =recyclerView.adapter as VideoAdapter
adapter.submitList(data)
}