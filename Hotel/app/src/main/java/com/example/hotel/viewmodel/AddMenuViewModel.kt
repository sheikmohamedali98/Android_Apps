package com.example.hotel.viewmodel



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.hotel.data.MenuDao
import com.example.hotel.data.MenuItem
import kotlinx.coroutines.*

class AddMenuViewModel(val dataBase: MenuDao,application: Application):AndroidViewModel(application){

    var menuItemName = MutableLiveData<String>()
    var menuItemPrice = MutableLiveData<String>()

    private val viewModelJob = Job()

   private val uiScope = CoroutineScope(Dispatchers.Main+viewModelJob)


    init{
        menuItemName.value = ""
        menuItemPrice.value = ""
    }

    fun add(){
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

        val menuItem = MenuItem(itemName = menuName, itemPrice = menuPrice.toString().toDouble())
        withContext(Dispatchers.IO){
            dataBase.addMenu(menuItem)
        }

    }


}

