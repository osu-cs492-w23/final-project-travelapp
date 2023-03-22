package com.example.travlingapp

import com.example.travlingapp.R
import com.example.travlingapp.data.DistanceResult
import retrofit2.Callback
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.travlingapp.api.GoogleMapService
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, DrivingInfoActivity::class.java)
        startActivity(intent)
    }


}


