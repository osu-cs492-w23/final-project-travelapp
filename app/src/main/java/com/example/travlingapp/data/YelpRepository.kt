package com.example.travlingapp.data

import com.example.travlingapp.api.YelpService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.travlingapp.BuildConfig

const val YELP_API_KEY = BuildConfig.YELP_API_KEY

class YelpRepository(
    private val service: YelpService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private var currentLocation: String? = null
    private var currentSort: String? = null
    private var cachedYelp: List<YelpRepo>? = null

    suspend fun loadRestaurantsSearch(location:String, sort: String): Result<List<YelpRepo>> {
        return if(location == currentLocation && sort == currentSort && cachedYelp != null){
            Result.success(cachedYelp!!)
        }
        else{
            currentLocation = location
            currentSort = sort
            withContext(dispatcher) {
                try{
                    val response = service.searchRestaurants(location, "Bearer $YELP_API_KEY", 30, sort)
                    if(response.isSuccessful){
                        Result.success(response.body()?.businesses ?: listOf())
                    }else{
                        Result.failure(Exception(response.errorBody()?.string()))
                    }
                }catch (e: Exception){
                    Result.failure(e)
                }

            }
        }
    }


}