package com.example.locationtracker

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationViewModel:ViewModel() {

    private  val _lattitude = MutableLiveData<String>()

    val lattitude:LiveData<String>
        get() = _lattitude

    private  val _longtitude = MutableLiveData<String>()
    val longtitude:LiveData<String>
        get() = _longtitude

//   val  client: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
//       Activity())

    init {
        _lattitude.value = "latitude"
        _longtitude.value = "longtitude"

    }




}