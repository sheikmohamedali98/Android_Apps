package com.example.movieapppagination.pagination

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapppagination.R

private const val IMAG_URL = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("imageThumnail")
fun getMovieThumnailImage(imageView: ImageView, imagurl: String?) {
        val url = IMAG_URL + imagurl
    Glide.with(imageView.context)
        .load(url) // image url
        .placeholder(R.drawable.error)
        .error(R.drawable.error)
        .centerCrop()
        .into(imageView);

    }

//        .placeholder(R.drawable.placeholder) // any placeholder to load at start
//        .error(R.drawable.imagenotfound)  // any image in case of error

