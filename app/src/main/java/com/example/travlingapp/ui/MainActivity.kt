package com.example.travlingapp.ui

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.Context
import android.graphics.PorterDuff
import android.location.Geocoder
import android.location.LocationManager
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.travlingapp.R
import com.google.android.gms.location.*
import java.util.Locale
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var inputBoxFrom: EditText

    // for get user location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private val LOCATION_PERMISSION_REQUEST_CODE = 100

    private var drving_option : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputBoxFrom= findViewById(R.id.input_box_from)
        val inputBoxTo: EditText = findViewById(R.id.input_box_to)

        val searchBtn: Button = findViewById(R.id.btn_search)
        val getLocationBtn: ImageButton = findViewById(R.id.btn_location)

        val drivingBtn : ImageButton = findViewById(R.id.btn_driving)
        val restaurantBtn : ImageButton = findViewById(R.id.btn_restaurant)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        //get user loction
        getLocationBtn.setOnClickListener {
            getLocation()
        }

        drivingBtn.setOnClickListener {
            drivingBtn.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN)
            drivingBtn.setBackgroundResource(R.drawable.t_driving_btn_style)
            restaurantBtn.setColorFilter(ContextCompat.getColor(this, R.color.not_very_black), PorterDuff.Mode.SRC_IN)
            restaurantBtn.setBackgroundResource(R.drawable.f_restaurant_btn_style)
            drving_option = true
        }

        restaurantBtn.setOnClickListener {
            drivingBtn.setColorFilter(ContextCompat.getColor(this, R.color.not_very_black), PorterDuff.Mode.SRC_IN)
            drivingBtn.setBackgroundResource(R.drawable.f_driving_btn_style)
            restaurantBtn.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN)
            restaurantBtn.setBackgroundResource(R.drawable.t_restaurant_btn_style)
            drving_option = false
        }


        searchBtn.setOnClickListener {
            val originCity = inputBoxFrom.text.toString()
            val destinationCity = inputBoxTo.text.toString()
            Log.d(TAG, "origin city: $originCity, destination city: $destinationCity, driving: $drving_option")
            if (drving_option) {
                drivingInfoClick(originCity, destinationCity)
            }
        }
    }

    //intent to driving page
    private fun drivingInfoClick(fromCityName: String, toCityName: String) {
        val intent = Intent(this, DrivingInfoActivity::class.java).apply {
            putExtra(EXTRA_DRIVE_FROM, fromCityName)
            putExtra(EXTRA_DRIVE_TO, toCityName)
        }
        startActivity(intent)
    }

    // for getting user location
    private fun getLocation() {
        if (checkPermission()){
            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val location = task.result
                    if (location == null) {
                        //if location is null, we will get new location
                        locationRequest = LocationRequest()
                        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        locationRequest.interval = 0
                        locationRequest.fastestInterval = 0
                        locationRequest.numUpdates = 2
                        fusedLocationProviderClient!!.requestLocationUpdates(
                            locationRequest, locationCallback, Looper.myLooper())
                    } else {
                        inputBoxFrom.setText(getCityName(location.latitude, location.longitude))
                    }
                }
            } else {
                Toast.makeText(this, "Please Enable your location service", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            requestPermission()
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            val lastLocation = p0.lastLocation
            inputBoxFrom.setText(getCityName(lastLocation!!.latitude, lastLocation!!.longitude))
        }
    }

    //check the users permission
    private fun checkPermission():Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    //get user permission
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    //if the permission is enabled
    private fun isLocationEnabled():Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    //get the location string
    private fun getCityName(lat: Double, long: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(lat, long, 1)

        return addresses!!.get(0).locality
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Permission enable")
            }
        }
    }
}