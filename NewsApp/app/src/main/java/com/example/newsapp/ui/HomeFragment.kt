package com.example.newsapp.ui


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.adapter.VideoAdapter
import com.example.newsapp.api.NewsFilter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.util.LoactionPermission

import com.example.newsapp.viewmodel.HomeViewModel
import com.example.newsapp.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {

//    lateinit var binding:Bin
    private  lateinit var  binding: FragmentHomeBinding
    private  lateinit var  viewModel: HomeViewModel
    private lateinit var  locationPermission: LoactionPermission
    private lateinit var locationManager: LocationManager
    private val PERMISSION_ID = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        val factory = HomeViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]

        locationPermission = LoactionPermission(activity as AppCompatActivity)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = VideoAdapter(VideoAdapter.OnClickListener{
            viewModel.displayData(it)
        })
        binding.recycleView.adapter = adapter


        viewModel.navigateToSelectedNews.observe(activity as AppCompatActivity, Observer {
            if(it!= null){
//                val extra = FragmentNavigatorExtras()
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailNewsFragment(it))
                viewModel.displayProperties()
            }
        })


        locationManager = (activity as MainActivity).getSystemService(Context.LOCATION_SERVICE) as LocationManager


        viewModel.listOfNews.observe(viewLifecycleOwner){
             adapter.submitList(it)
        }
        setHasOptionsMenu(true)

       if (ActivityCompat.checkSelfPermission(activity as MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity as MainActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(activity as MainActivity,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_ID)
        }
          val location =   locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)


        var cityName:String ="madurai"
       if (location != null) {
           cityName = locationPermission.geoCoderConverter(location.latitude,
               location.longitude)?.subAdminArea.toString()
        }
        Toast.makeText(activity, "${cityName}", Toast.LENGTH_LONG).show()
        viewModel.getWeather(cityName)
        binding.weatherTv.text = viewModel.getWeather(cityName).toString()
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.over_flow_menu,menu)
    }

    override  fun onOptionsItemSelected(item: MenuItem): Boolean {
  viewModel.updateFilter(  when(item.itemId){
        R.id.sport->NewsFilter.SPORT
        R.id.allNews->NewsFilter.ALL
        R.id.technology->NewsFilter.TECHNOLOGY
      else -> NewsFilter.ALL
  }
  )
    return true
    }


//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when (requestCode) {
//            1 -> {
//                if (grantResults.isNotEmpty() && grantResults[0] ==
//                    PackageManager.PERMISSION_GRANTED
//                ) {
//                    if ((context?.let {
//                            ContextCompat.checkSelfPermission(it,
//                                Manifest.permission.ACCESS_FINE_LOCATION)
//                        } ===
//                                PackageManager.PERMISSION_GRANTED)) {
//                        Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show()
//                }
//                return
//            }
//        }
//
//
//        fun permissionCheck() {
//            if (context?.let {
//                    ContextCompat.checkSelfPermission(it,
//                        Manifest.permission.ACCESS_FINE_LOCATION)
//                } !==
//                PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,
//                        Manifest.permission.ACCESS_FINE_LOCATION)
//                ) {
//                    ActivityCompat.requestPermissions(context as Activity,
//                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
//                } else {
//                    ActivityCompat.requestPermissions(context as Activity,
//                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
//                }
//            }
//        }
//
//    }

    fun getPermission(){

        if(locationPermission.checkPermission()){
            locationPermission.requestPermission()
        }
    }



    }
//fun getCityName(longtitude:Double,latitude:Double){
//
//    var cityName:String = "NotFound"
//    val geocoder = Geocoder(MainActivity)
//
//}


