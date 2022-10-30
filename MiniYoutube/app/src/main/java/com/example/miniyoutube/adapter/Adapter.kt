package com.example.miniyoutube.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.miniyoutube.domain.VideoModel

@BindingAdapter("imagUrl")
fun getImage(imageView: ImageView,srcUrl:String?){
    srcUrl.let {
        val imgUri = it?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(RequestOptions())
            .into(imageView)
    }
}

//@BindingAdapter("listVideo")
//fun bindRecycleView(recyclerView: RecyclerView, data:List<VideoModel>?){
//    val adapter = recyclerView.adapter as VideoAdapter
//    adapter.submitList(data)
//}