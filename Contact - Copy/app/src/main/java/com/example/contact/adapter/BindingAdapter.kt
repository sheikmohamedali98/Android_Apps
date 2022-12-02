package com.example.contact.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.contact.R
import com.github.dhaval2404.imagepicker.ImagePicker

@BindingAdapter("image_url")
fun setImage(imageView: ImageView,imageurl:String){
    Glide.with(imageView.context)
        .load(imageurl) // image url
        .placeholder(R.drawable.ic_baseline_person_24) // any placeholder to load at start
        .override(200, 200) // resizing
        .centerCrop()
        .into(imageView)
}