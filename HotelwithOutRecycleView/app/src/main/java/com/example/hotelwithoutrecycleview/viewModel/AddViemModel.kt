package com.example.hotelwithoutrecycleview.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelwithoutrecycleview.data.MenuDao
import com.example.hotelwithoutrecycleview.data.MenuItem
import kotlinx.coroutines.*

class AddViemModel(private val database:MenuDao, application: Application):AndroidViewModel(application) {

    var menuItemName = MutableLiveData<String>()
    var menuItemPrice = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+viewModelJob)

    init {
        menuItemName.value = ""
        menuItemPrice.value = ""
    }

    fun  add(){
        if(menuItemName.value?.isNotEmpty()?:false && menuItemPrice.value?.isNotEmpty()?:false ){
            val menuName = menuItemName.value?:""
            val menuPrice = menuItemPrice.value?:""
            uiScope.launch {
                addMenuItem(menuName,menuPrice)
             }
            menuItemName.value = ""
            menuItemPrice.value = ""
        }

    }

    private suspend fun addMenuItem(menuName: String, menuPrice: String) {

        val menuItem = MenuItem(itemName = menuName, itemPrice = menuPrice)
        withContext(Dispatchers.IO){
            database.insert(menuItem)
        }

    }


}