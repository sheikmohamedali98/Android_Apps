package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class WebViewViewModel(application:Application) :AndroidViewModel(application){

    companion object{
        var url:String = ""
    }

}