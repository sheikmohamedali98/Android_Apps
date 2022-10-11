package com.example.hotelwithoutrecycleview.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.hotelwithoutrecycleview.data.MenuDao
import com.example.hotelwithoutrecycleview.data.MenuItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class DisplayViewModel(dataBase:MenuDao,application: Application):AndroidViewModel(application){

    var displayData =MutableLiveData<String>()
    val allMenuItem = dataBase.getAllMenuItem()
    val menuString = Transformations.map(allMenuItem){
        allMenuItem -> parseToString(allMenuItem)
    }

   private val viewModelJob  = Job()
    val uiScop = CoroutineScope(Dispatchers.Main+viewModelJob)



    private fun parseToString(allMenuItem:List<MenuItem>):String {

        val stringBuilder:StringBuilder = StringBuilder()
        for(menus in allMenuItem){
            stringBuilder.append(" Item Name :${menus.itemName} \n ItemPrice:${menus.itemPrice} \n\n")
        }
        return stringBuilder.toString()
    }


}