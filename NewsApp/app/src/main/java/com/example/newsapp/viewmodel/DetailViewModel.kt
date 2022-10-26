package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.newsapp.domain.DomainData

class DetailViewModel(application:Application) :AndroidViewModel(application){

    companion object{
        var url:String = ""
    }

}