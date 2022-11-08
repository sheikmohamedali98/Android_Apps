package com.example.locationpermision.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import  android.provider.Settings
import com.example.locationpermision.BuildConfig
import com.example.locationpermision.databinding.FragmentGeometricBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar


class GeometricFragment : Fragment() {
    private lateinit var  binding: FragmentGeometricBinding
    private  lateinit var locationManager:LocationManager
    private lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGeometricBinding.inflate(inflater,container,false)

        binding.gpsBtn.setOnClickListener {
            location()
        }
        binding.camera.setOnClickListener {
            startCamera()
        }
//        Snackbar.make(binding.root, "My Message", Snackbar.LENGTH_SHORT).show()

        requestPerMission()
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        return binding.root
    }


    val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){permission->
        permission.forEach { actionMap->
            when(actionMap.key){
                Manifest.permission.CAMERA->if(actionMap.value) {
                    Toast.makeText(activity,
                    "granted camera",
                    Toast.LENGTH_SHORT).show()
                } else Snackbar.make(binding.root,"permission Dined for Camera",Snackbar.LENGTH_LONG).setAction("Setting"){gotoAppInfo()}.show()

                Manifest.permission.ACCESS_FINE_LOCATION->if(actionMap.value) {Toast.makeText(activity,
                    "granted  Fine Location",
                    Toast.LENGTH_SHORT).show() }else {Snackbar.make(binding.root,"permission Dined for Fine  Location",Snackbar.LENGTH_LONG).setAction("Setting"){gotoAppInfo()}.show()}
                Manifest.permission.ACCESS_COARSE_LOCATION->if(actionMap.value) {Toast.makeText(activity,
                    "granted Coarse Location",
                    Toast.LENGTH_SHORT).show() }else {Snackbar.make(binding.root,"permission Dined for Coarse Location",Snackbar.LENGTH_LONG).setAction("Setting"){gotoAppInfo()}.show()}
            }
        }

    }

    fun checkPermission():Boolean = ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun locationEnabled():Boolean = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    fun location(){
        if(checkPermission()){
            if(locationEnabled()){
                Toast.makeText(activity, "get Location", Toast.LENGTH_SHORT).show()
            }else{
                Snackbar.make(binding.root," On your location  For Location",Snackbar.LENGTH_INDEFINITE).setAction("location"){goToLocationSettings()}.show()
            }

        }else{
//Snackbar.make(binding.root,"Require Permission For Location",Snackbar.LENGTH_INDEFINITE).setAction("setting"){goToLocationSettings()}.show()
            requestPerMission()
        }
    }

    fun checkPermissionCamera():Boolean = ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

fun startCamera(){
    if(checkPermissionCamera()){
        Toast.makeText(activity, "get Camera Action", Toast.LENGTH_SHORT).show()

    }else{
        requestPerMission()
    }
}

fun requestPerMission(){
    requestMultiplePermissions.launch(
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

}

    private fun gotoAppInfo(){
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package",BuildConfig.APPLICATION_ID,null)
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

        //    Settings.ACTION_LOCATION_SOURCE_SETTINGS
    private fun goToLocationSettings() {
        val settingsIntent = Intent()
        settingsIntent.action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
        settingsIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(settingsIntent)
    }

    fun getLocationMap(){
//        fusedLocationProviderClient.
    }
}