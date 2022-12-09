package com.example.contact.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.contact.R

@BindingAdapter("image_url")
fun setImage(imageView: ImageView,imageurl:String){

    if(imageurl.equals("")) {
        Glide.with(imageView.context)
            .load(R.drawable.ic_baseline_person_24)
           // any placeholder to load at start
            .override(200, 200) // resizing
            .centerCrop()
            .into(imageView)
        println("Inside Binding Adapter")
    }else{
        Glide.with(imageView.context)
            .load(imageurl) // image url
            .override(200, 200) // resizing
            .centerCrop()
            .into(imageView)
    }
}

//fun  randomImage(name:String):Int{
//    when(name.get(0)){
//
//    }
//}


//val generator = ColorGenerator.MATERIAL
//
//val color:Int = generator.randomColor
//
//val textDrawable = TextDrawable.Builder()
//    .setWidth(100)
//    .setBold()
//    .setHeight(100)
//    .setColor(color)
//    .build()