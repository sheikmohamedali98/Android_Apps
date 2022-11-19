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
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.adapter.VideoAdapter
import com.example.newsapp.api.NewsFilter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.domain.DomainData
import com.example.newsapp.util.LoactionPermission

import com.example.newsapp.viewmodel.HomeViewModel
import com.example.newsapp.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    //    lateinit var binding:Bin
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var locationPermission: LoactionPermission
    private lateinit var locationManager: LocationManager
    private val PERMISSION_ID = 1
    private lateinit var adapter: VideoAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        var isSearch:Boolean = false
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val factory = HomeViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        locationPermission = LoactionPermission(activity as AppCompatActivity)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = VideoAdapter(VideoAdapter.OnClickListener {
            viewModel.displayData(it)
        })
        binding.recycleView.adapter = adapter


        viewModel.navigateToSelectedNews.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailNewsFragment(it))
                viewModel.displayProperties()
            }
        })


//        locationManager =
//            (activity as MainActivity).getSystemService(Context.LOCATION_SERVICE) as LocationManager


        viewModel.listOfNews.observe(viewLifecycleOwner) {
//            if(it.isEmpty()){
//                if(isSearch){
//                    adapter.submitList(it)
//                }
//                isSearch = false
//            }else{
//
//                adapter.submitList(it)
//            }

            adapter.submitList(it)
        }
        setHasOptionsMenu(true)
//
//        if (ActivityCompat.checkSelfPermission(activity as MainActivity,
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                activity as MainActivity,
//                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(activity as MainActivity,
//                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
//                    Manifest.permission.ACCESS_FINE_LOCATION),
//                PERMISSION_ID)
//        }
//          val location =   locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)


//        var cityName:String ="madurai"
//       if (location != null) {
//           cityName = locationPermission.geoCoderConverter(location.latitude,
//               location.longitude)?.subAdminArea.toString()
//        }
//        Toast.makeText(activity, "${cityName}", Toast.LENGTH_LONG).show()
//        viewModel.getWeather("chennai")
//        binding.weatherTv.text = viewModel.getWeather("chennai").toString()


//        viewModel.getWeatherDetails("12.831597","80.0518445")
//        viewModel.myResponse.observe(viewLifecycleOwner, Observer {response->
//            if(response.isSuccessful){
//                Toast.makeText(activity, response.body().toString(), Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(activity, response.code().toString(), Toast.LENGTH_SHORT).show()
//
//            }
//
//        })




        return binding.root
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.over_flow_menu, menu)
        val search = menu.findItem(R.id.menuSearch)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object:OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    Toast.makeText(activity, "Search box workin", Toast.LENGTH_LONG).show()
                    searchDatabase(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchDatabase(newText)
                }
                return true
            }

        })
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(when (item.itemId) {
            R.id.sport -> NewsFilter.SPORT
            R.id.allNews -> NewsFilter.ALL
            R.id.technology -> NewsFilter.TECHNOLOGY
            else -> NewsFilter.ALL
        }
        )
        return true
    }



    fun searchDatabase(query: String = "leader") {
        val searchQyery = "${query}%"


        GlobalScope.launch {
            var temp = viewModel.searchDatabase(searchQyery)
        }


//        viewModel.searchDatabase(searchQyery).observe(this, Observer { list ->
//            list?.let {
//                adapter.submitList(it as MutableList<DomainData>)
//            }
//
//        })
    }


}





