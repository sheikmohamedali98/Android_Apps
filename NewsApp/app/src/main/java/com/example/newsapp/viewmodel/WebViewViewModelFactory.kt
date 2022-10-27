package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WebViewViewModelFactory(var application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WebViewViewModel::class.java)){
            return WebViewViewModel::class.java as T
        }
        throw IllegalArgumentException("Model Class Not Found")
    }
}