package com.example.miniyoutube.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.miniyoutube.adapter.VideoAdapter
import com.example.miniyoutube.databinding.FragmentHomeBinding
import com.example.miniyoutube.viewmodel.HomeViewModel
import com.example.miniyoutube.viewmodel.HomeViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class HomeFragment : Fragment() {

    lateinit var  binding:FragmentHomeBinding
    private lateinit var viewModel:HomeViewModel
    private lateinit var client: FusedLocationProviderClient
    private val PERMISSION_CODE = 1
//    lateinit var location: Location
//    private lateinit var latitude:String
//    private lateinit var lontitude:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
//        binding = ?

       val factory =HomeViewModelFactory(requireActivity().application)
        val adapter = VideoAdapter()


        viewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recycleView.adapter = adapter
//        binding.recycleView.adapter = VideoAdapter(VideoAdapter.OnClickListner{
////          ad
//        })
//
//        viewModel.response.observe(viewLifecycleOwner, Observer {
//            adapter.submitList(it)
//        })



//       latitude = location.latitude.toString()
//        lontitude = location.longitude.toString()

        client = LocationServices.getFusedLocationProviderClient(requireActivity())
//            var  id= android.os.Process.myPid();
//        println("\n\n\n\n${id}\n\n\n\n")

        binding.floatingActionButton.setOnClickListener {
            getCurrentLocation()
//            val action = HomeFragmentDirections.actionHomeFragmentToWeatherFragment(latitude,lontitude)
//            findNavController().navigate(action)
        }

        viewModel.playList.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(activity, adapter.submitList(it).toString(), Toast.LENGTH_SHORT).show()
            adapter.submitList(it)
        })
        return binding.root
    }



    fun getCurrentLocation() {
        if (checkPermission()) {
            //if permission given but location of
            if (isLocationEnabled()) {

//
                //final Longtitude and Longtitude
//                client.getCurrentLocation()
                client.lastLocation.addOnCompleteListener(requireActivity()) { task ->

                     val location:Location = task.result

                    if (location == null) {
                        Toast.makeText(activity, "Null return ", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, "get Sucess  ", Toast.LENGTH_SHORT).show()
//                        viewModel.lattitude.observe(requireActivity(), Observer {
//                            binding.lattiyudeAns.text = location?.latitude.toString()
//                        })
//                        viewModel.longtitude.observe(requireActivity(), Observer {
//                            lontitude = location?.longitude.toString()
//                        })
//                        viewModel.latitude.observe(requireActivity(), Observer {
//                            latitude = location.latitude.toString()
//                        })
//                        binding.lattiyudeAns.text = location.latitude.toString()
//                        binding.lontitudeAns.text = location.longitude.toString()
//                        Toast.makeText(activity, location.latitude.toString(), Toast.LENGTH_SHORT).show()
//                        Toast.makeText(activity, location.longitude.toString(), Toast.LENGTH_SHORT).show()


                    }

                }


            } else {
                //setting activity
                Toast.makeText(activity, "Turn on Location", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
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