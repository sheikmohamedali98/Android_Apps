package com.example.hotelwithoutrecycleview.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hotelwithoutrecycleview.data.MenuDao
import com.example.hotelwithoutrecycleview.data.MenuDataBase

class addViewModelFactory(val dataBase: MenuDao, val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddViemModel::class.java)){
            return AddViemModel(dataBase, application) as T
        }
        throw IllegalArgumentException("calss Not Found")
    }

}