package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.domain.DomainData
import com.example.newsapp.ui.DetailNewsFragmentArgs

class DetailViewModelFactory(private val domainData: DomainData, val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsViewModel::class.java)){
            return DetailsViewModel(domainData,application) as T
        }
        throw  IllegalArgumentException("Factory not Create")
    }
}