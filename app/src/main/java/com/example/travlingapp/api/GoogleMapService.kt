package com.example.travlingapp.api

import com.example.travlingapp.data.DistanceResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleMapService {
    //https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=YOUR_API_KEY

   @GET("distancematrix/json?")
    fun loadDistanceMatrix(
       @Query("origins") origin: String?,
       @Query("destinations") destination: String?,
       @Query("key") apikey: String?
   ):   Call<DistanceResult>

    companion object{
        private const val BASE_URL = "https://maps.googleapis.com/maps/api/"
        fun create() : GoogleMapService{
            return Retrofit.Builder()       //instance of service
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(GoogleMapService::class.java)
        }

    }


}
