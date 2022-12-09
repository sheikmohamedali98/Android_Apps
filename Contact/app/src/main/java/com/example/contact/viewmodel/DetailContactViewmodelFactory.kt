package com.example.contact.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailContactViewmodelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailContactViewmodel::class.java)) {
            return DetailContactViewmodel(application) as T

        }
        throw IllegalArgumentException("cannot Created")
    }
}