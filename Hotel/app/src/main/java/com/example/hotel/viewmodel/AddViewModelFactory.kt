package com.example.hotel.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hotel.data.MenuDao

class AddViewModelFactory (val dataBase:MenuDao, val application: Application):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddMenuViewModel::class.java)) {
            return AddMenuViewModel(dataBase,application) as T
        }
        throw IllegalArgumentException("model class not found")
    }
}


