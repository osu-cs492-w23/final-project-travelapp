package com.example.travlingapp.data

import com.example.travlingapp.api.YelpService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YelpRepository(
    private val service: YelpService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private var currentLocation: String? = null
    private var currentSort: String? = null
    private var cachedYelp: List<YelpRepo>? = null

    private val API_KEY = "HQMa3igY3Q1T99yVXGgtBi1vJ9BG1S5-CWfVIbFX8GRFnMwuI-pmwCyYLXqSu_wR4TFGkae88zOBt3eOKKdQw2LLq-DIFToB_nXhgZvkAdIAyZHawD5f4v4FZ4MGZHYx"
    suspend fun loadRestaurantsSearch(location:String, sort: String = "rating"): Result<List<YelpRepo>> {
        return if(location == currentLocation && sort == currentSort && cachedYelp != null){
            Result.success(cachedYelp!!)
        }
        else{
            currentLocation = location
            currentSort = sort
            withContext(dispatcher) {
                try{
                    val response = service.searchRestaurants(location, "Bearer $API_KEY", 30, sort)
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