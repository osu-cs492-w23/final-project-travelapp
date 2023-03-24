package com.example.travlingapp.api

import com.example.travlingapp.data.YelpSearchResults
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpService {
    @GET("businesses/search")
    fun searchRestaurants(
        @Query ("location") location: String,
        @Header("Authorization") authHeader: String = "HQMa3igY3Q1T99yVXGgtBi1vJ9BG1S5-CWfVIbFX8GRFnMwuI-pmwCyYLXqSu_wR4TFGkae88zOBt3eOKKdQw2LLq-DIFToB_nXhgZvkAdIAyZHawD5f4v4FZ4MGZHYx",
        @Query ("limit") limit: Int = 30,
        @Query("sort_by") sort_bY: String,
        //best_match, rating, review_count, distance
    ) : Call<YelpSearchResults>


    companion object{
        private const val BASE_URL = "https://api.yelp.com/v3/"

        fun create() : YelpService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(YelpService::class.java)
        }
    }
}