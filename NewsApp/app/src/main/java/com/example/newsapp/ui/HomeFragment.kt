package com.example.newsapp.ui


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.adapter.VideoAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.domain.weather.Location
import com.example.newsapp.util.LoactionPermission

import com.example.newsapp.viewmodel.HomeViewModel
import com.example.newsapp.viewmodel.HomeViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

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
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        val factory = HomeViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]

        locationPermission = LoactionPermission(activity as AppCompatActivity)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = VideoAdapter().also {
            it.setOnclickListener { news ->
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(news))
            }
        }
//        val list:List<>z = DomainData("Anmol Sharma","Talking about India-Pakistan T20 World Cup 2022 match which India won on last ball","25 Oct 2022,Tuesday","faa47543c0e84474a2443e39bb701d22","https://static.inshorts.com/inshorts/images/v1/variants/jpg/m/2022/10_oct/25_tue/img_1666671280427_494.jpg?","https://www.hindustantimes.com/cricket/just-stop-the-world-cup-there-australia-star-s-mitchell-marsh-epic-remark-after-drama-filled-mcg-clash-between-india-and-pakistan-101666604175869-amp.html?utm_campaign=fullarticle&utm_medium=referral&utm_source=inshorts","12121","Hello","qwertokjcvbnm,")
        binding.recycleView.adapter = adapter


//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        locationManager = (activity as MainActivity).getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        locationManager =
//        val lisSize = viewModel.listOfNews.value
//        Toast.makeText(activity, "${lisSize}", Toast.LENGTH_LONG).show()

        viewModel.listOfNews.observe(viewLifecycleOwner){
             adapter.submitList(it)
//                println("\n\n\n${adapter.submitList(it)}\n\n")
        }
//getPermission()
//        var location = lo
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

        var cityName:String ="Not Found"
       if (location != null) {
           cityName = locationPermission.geoCoderConverter(location.latitude,
               location.longitude)?.subAdminArea.toString()
            Toast.makeText(activity, "${cityName}", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

//    private fun permissionCheck() {
//        TODO("Not yet implemented")
//    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.over_flow_menu,menu)
//    }

//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.sport->viewModel.getNewsProperties("sports")
//
//        }
////            viewModel.updateFilter(  when(item.itemId){
////                R.id.sport->viewModel.getNewsProperties("sports")
////                R.id.business->NewsFilter.BUSINESS
////                R.id.autoMobile->NewsFilter.AUTOMOBILE
////                R.id.technology->NewsFilter.TECHNOLOGY
////                else -> {NewsFilter.ALL}
////            })
//                return super.onOptionsItemSelected(item);
//        }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
        R.id.sport-> Toast.makeText(activity, "Sport Clicked", Toast.LENGTH_SHORT).show()
        R.id.allNews->Toast.makeText(activity, "All News Clicked", Toast.LENGTH_SHORT).show()
        R.id.technology->Toast.makeText(activity, "Technology Clicked", Toast.LENGTH_SHORT).show()
    }
        return super.onOptionsItemSelected(item)
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


