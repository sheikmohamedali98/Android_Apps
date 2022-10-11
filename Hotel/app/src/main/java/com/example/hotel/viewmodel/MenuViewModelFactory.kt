package com.example.hotel.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hotel.data.MenuDao

class MenuViewModelFactory(private val dataSource:MenuDao, private val application: Application):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MenuViewModel::class.java)){
            return MenuViewModel(dataSource,application) as T
        }
        throw  IllegalArgumentException("unknow View Model Class")
    }

}