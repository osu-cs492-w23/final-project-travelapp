package com.example.travlingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.travlingapp.R
import com.example.travlingapp.api.YelpService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val yelpService = YelpService.create()
    private val API_KEY = "HQMa3igY3Q1T99yVXGgtBi1vJ9BG1S5-CWfVIbFX8GRFnMwuI-pmwCyYLXqSu_wR4TFGkae88zOBt3eOKKdQw2LLq-DIFToB_nXhgZvkAdIAyZHawD5f4v4FZ4MGZHYx"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        yelpService.searchRestaurants("Bearer $API_KEY","Avocado Toast", "New York", 30)
            .enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.i("MainActivity", "response: $response")
//                    Log.i("MainActivity", "response: ${response.body()}")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.i("MainActivity", "fail: $t")
                }
            })
    }
}