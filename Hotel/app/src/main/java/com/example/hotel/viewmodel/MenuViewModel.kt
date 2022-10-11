package com.example.hotel.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.hotel.data.MenuDao
import kotlinx.coroutines.*

class MenuViewModel(val dataBase: MenuDao, application: Application) :
    AndroidViewModel(application) {

    val data = dataBase.displayItem()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+viewModelJob)



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}