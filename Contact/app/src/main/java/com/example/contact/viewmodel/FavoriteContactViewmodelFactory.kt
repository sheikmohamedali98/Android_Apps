package com.example.contact.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoriteContactViewmodelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteContactViewmodel::class.java)) {
            return FavoriteContactViewmodel(application) as T
        }
        throw IllegalArgumentException("Model Factory Cannot Created")
    }
}