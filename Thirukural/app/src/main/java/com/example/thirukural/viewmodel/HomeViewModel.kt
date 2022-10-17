package com.example.thirukural.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirukural.model.Thirukural
import com.example.thirukural.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private  val repository: Repository):ViewModel() {

//    private  val _myKural = MutableLiveData<Response<Thirukural>>()
//
//    val myKural:LiveData<Response<Thirukural>>
//    get() = _myKural

    private  val _myKural = MutableLiveData<List<String>>()

    val myKural:LiveData<List<String>>
        get() = _myKural

    private val _kural = MutableLiveData<String>()

    val kural:LiveData<String>
    get() = _kural

     fun getKurals(number:Int){
         var temp = ""
        viewModelScope.launch {
            val response = repository.getKurals(number)
            temp += (
                    "\nகுறள் எண் = ${response.body()?.number}" +
                    "\nபிரிவு = ${response.body()?.sect_tam}" +
                    "\nகுறள் = ${response.body()?.line1}" +
                            "\n${response.body()?.line2}"
                    )
//            _myKural.value = _myKural.value.add()
            _kural.value = temp
        }
    }

}