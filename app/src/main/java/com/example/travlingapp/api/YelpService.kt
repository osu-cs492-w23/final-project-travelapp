package com.example.travlingapp.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpService {

    @GET("businesses/search")
    fun  searchRestaurants(
        @Header("Authorization") authHeader: String = "HQMa3igY3Q1T99yVXGgtBi1vJ9BG1S5-CWfVIbFX8GRFnMwuI-pmwCyYLXqSu_wR4TFGkae88zOBt3eOKKdQw2LLq-DIFToB_nXhgZvkAdIAyZHawD5f4v4FZ4MGZHYx",
        @Query ("location") location: String,
        @Query ("limit") limit: Int,
    ) : Call<String>

    companion object{
        private const val BASE_URL = "https://api.yelp.com/v3/"

        fun create() : YelpService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(YelpService::class.java)
        }
    }
}