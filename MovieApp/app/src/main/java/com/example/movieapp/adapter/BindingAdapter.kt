package com.example.movieapp.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun getMovieImage(imageView: ImageView, imagurl:String?) {
    imagurl?.let {
        val imaguri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imaguri)
            .apply(RequestOptions())
            .into(imageView)
    }
}