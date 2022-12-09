package com.example.contact.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateViewmodelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateViewmodel::class.java)) {
            return UpdateViewmodel(application) as T
        }
        throw IllegalArgumentException("model cannot Created")
    }
}