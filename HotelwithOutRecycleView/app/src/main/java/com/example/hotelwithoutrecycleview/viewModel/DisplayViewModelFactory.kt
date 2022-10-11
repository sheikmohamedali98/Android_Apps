package com.example.hotelwithoutrecycleview.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hotelwithoutrecycleview.data.MenuDao

class DisplayViewModelFactory(var dataSource:MenuDao, var application: Application):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DisplayViewModel::class.java)){
            return DisplayViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("model Class Not found")
    }
}