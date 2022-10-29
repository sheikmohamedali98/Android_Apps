package com.example.locationtracker

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.locationtracker.databinding.FragmentLocationTrackerBinding
import com.google.android.gms.common.api.GoogleApi.Settings
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

private const val PERMISSION_CODE = 100

class LocationTrackerFragment : Fragment() {

    private lateinit var binding: FragmentLocationTrackerBinding
    private lateinit var viewModel: LocationViewModel
    lateinit var client: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLocationTrackerBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LocationViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this



        client = LocationServices.getFusedLocationProviderClient(
            requireActivity())


        binding.resultBtn.setOnClickListener {
//            if(ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
//                getCurrentLocation()
//            }else{
//                requestPermissions(arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),1)
//            }

            getCurrentLocation()

        }

        return binding.root
    }


    fun getCurrentLocation() {
        if (checkPermission()) {
            //if permission given but location of
            if (isLocationEnabled()) {

//
                //final Longtitude and Longtitude
                client.lastLocation.addOnCompleteListener(requireActivity()) { task ->

                    val location: Location? = task.result

                    if (location == null) {
                        Toast.makeText(activity, "Null return ", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, "get Sucess  ", Toast.LENGTH_SHORT).show()
                        viewModel.lattitude.observe(requireActivity(), Observer {
                            binding.lattiyudeAns.text = location?.latitude.toString()
                        })
                        viewModel.longtitude.observe(requireActivity(), Observer {
                            binding.lontitudeAns.text = location?.longitude.toString()
                        })
//                        binding.lattiyudeAns.text = location.latitude.toString()
//                        binding.lontitudeAns.text = location.longitude.toString()

                    }

                }


            } else {
                //setting activity
                Toast.makeText(activity, "Turn on Location", Toast.LENGTH_SHORT).show()
                val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }

        } else {
//request Permission
            requestPermission()
        }

    }

    private fun isLocationEnabled(): Boolean {

        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_CODE)
    }

    private fun checkPermission(): Boolean {

        if (ActivityCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "Granted", Toast.LENGTH_SHORT).show()
                getCurrentLocation()
            } else {
                Toast.makeText(activity, "Denied", Toast.LENGTH_SHORT).show()

            }
        }

    }



}

