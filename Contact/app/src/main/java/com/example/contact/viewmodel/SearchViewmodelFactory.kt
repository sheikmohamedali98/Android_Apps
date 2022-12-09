package com.example.contact.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchViewmodelFactory(private val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewmodel::class.java)){
            return SearchViewmodel(application) as T
        }
        throw  IllegalArgumentException("factory cannot created")
    }
}