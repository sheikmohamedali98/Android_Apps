package com.example.newsapp.util
//
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

//
//
//private const val REQUEST_INTERVEL = 500L
class LoactionPermission(private val activity: AppCompatActivity) {

    val PERMISSION_ID = 1


    fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(activity,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(activity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID)
    }

    fun requestNewLocationData() {

    }

    //val PERMISSION_ID = 1
//    private val locationManager =
//        activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//
//    private var fusedLocationProviderClient: FusedLocationProviderClient =
//        LocationServices.getFusedLocationProviderClient(activity)
//
//    private var locationRequest: LocationRequest = LocationRequest.Builder(LocationRequest.PRIORITY_HIGH_ACCURACY,
//        REQUEST_INTERVEL).build()
//    var isLocationRequsting = false
//    lateinit var locationCallback : LocationCallback
//
//
//    val isLocationUsable: Boolean
//        get() = checkLocationPermission() && checkLocationEnabled()
//
//    init {
//
//    }
//
//    fun checkLocationPermission(): Boolean = (
//            ActivityCompat.checkSelfPermission(
//                activity,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED ||
//                    ActivityCompat.checkSelfPermission(
//                        activity,
//                        Manifest.permission.ACCESS_FINE_LOCATION
//                    ) == PackageManager.PERMISSION_GRANTED
//            )
//
//    fun checkLocationEnabled(): Boolean {
//
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
//                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//    }
//
//    /**
//     * just requests permission
//     */
//    fun requestLocationPermission() {
//        ActivityCompat.requestPermissions(
//            activity, arrayOf(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ), PERMISSION_ID
//        )
//    }
//
//    fun requestLastLocation(onSuccess: (location: Location) -> Unit) {
//        if(checkLocationPermission())
//            fusedLocationProviderClient.lastLocation.addOnSuccessListener(activity) {
//                onSuccess(it)
//            }
//    }
//    /**
//     * requests for location get location once,
//     * send callback as parameter
//     *
//     * */
//    fun requestCurrentLocation(onSuccess: (location: Location) -> Unit) {
//        isLocationRequsting = true
//        locationCallback = object : LocationCallback() {
//            override fun onLocationResult(result: LocationResult) {
//                super.onLocationResult(result)
//                onSuccess(result.locations[0])
//                stopRequestingLocation()
//            }
//        }
//        if(checkLocationPermission())
//            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
//    }
//    /**
//     * requests for location continuously,
//     * send callback as parameter
//     *
//     * */
//    fun startRequestingCurrentLocation(onSuccess: (location: Location) -> Unit) {
//        isLocationRequsting = true
//        locationCallback = object : LocationCallback() {
//            override fun onLocationResult(result: LocationResult) {
//                super.onLocationResult(result)
////                onSuccess(result.locations.get(0))
//            }
//        }
//        if(checkLocationPermission())
//            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
//    }
//
//    /**
//     * use this function to stop requesting location
//     * */
//    fun stopRequestingLocation() {
//        if (::locationCallback.isInitialized) {
//            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
//            isLocationRequsting = false
//        }
//    }
//    //add geocoder to convert the co ordinate to city and vice versa
//
//    /**
//     * co ordinate to address(nullable)
//     * */
    fun geoCoderConverter(latitude: Double, longitude: Double): Address? {
        val geocoder = Geocoder(activity)
        var cityName = "Not Found"
        var address: Address? = null
        try {
            address = geocoder.getFromLocation(latitude, longitude, 1).get(0)


        } catch (e: Exception) {

        }
        return address
    }
//    /**
//     * city name to co ordinate(nullable)
//     * */
//    fun geoCoderConverter(city:String):Address?{
//        val geocoder = Geocoder(activity)
//        var address:Address?=null
//        try {
//            address = geocoder.getFromLocationName(city,1).get(0)
//        }
//        catch (e:Exception)
//        {
//            Log.i("TAG",e.message.toString())
//        }
//        return address
//    }
//    companion object{
//        private lateinit var INSTANCE:LoactionPermission
//        fun getInstance(_activity:AppCompatActivity):LoactionPermission{
//            if (!(::INSTANCE.isInitialized))
//            {
//                INSTANCE = LoactionPermission(_activity)
//            }
//            return INSTANCE
//        }
//    }
//}
//
//
//
}