package com.example.travlingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.tiles.material.CircularProgressIndicator
import com.example.travlingapp.R
import com.example.travlingapp.api.YelpService
import com.example.travlingapp.data.YelpSearchResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {
    private val API_KEY = "HQMa3igY3Q1T99yVXGgtBi1vJ9BG1S5-CWfVIbFX8GRFnMwuI-pmwCyYLXqSu_wR4TFGkae88zOBt3eOKKdQw2LLq-DIFToB_nXhgZvkAdIAyZHawD5f4v4FZ4MGZHYx"

    private val yelpService = YelpService.create()
    private val yelpRepoAdapter = YelpRepoListAdapter()

    private lateinit var searchResultListRV: RecyclerView
    private lateinit var errorMessageTV: TextView
    private lateinit var loadingIndicator: com.google.android.material.progressindicator.CircularProgressIndicator



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBoxET: EditText = findViewById(R.id.et_search_box)
        val searchBtn: Button = findViewById(R.id.btn_search)

        errorMessageTV = findViewById(R.id.tv_error_message)
        loadingIndicator = findViewById(R.id.loading_indicator)

        searchResultListRV = findViewById(R.id.rv_search_results)
        searchResultListRV.layoutManager = LinearLayoutManager(this)
        searchResultListRV.setHasFixedSize(true)
        searchResultListRV.adapter = yelpRepoAdapter

        searchBtn.setOnClickListener {
            val location = searchBoxET.text.toString()
            if(!TextUtils.isEmpty(location)){
                doRepoSearch(location)
                searchResultListRV.scrollToPosition(0)
            }
        }
    }


    private fun doRepoSearch(location: String) {
        loadingIndicator.visibility = View.VISIBLE
        yelpService.searchRestaurants(location, "Bearer $API_KEY", 30)
            .enqueue(object : Callback<YelpSearchResults> {
                override fun onResponse(call: Call<YelpSearchResults>, response: Response<YelpSearchResults>) {
                    loadingIndicator.visibility = View.INVISIBLE

                    Log.d("MainActivity", "Status code: ${response.body()}")
                    if (response.isSuccessful) {
                        yelpRepoAdapter.updateRepoList(response.body()?.businesses)
                        searchResultListRV.visibility = View.VISIBLE
                        errorMessageTV.visibility = View.INVISIBLE
                    } else {
                        errorMessageTV.text = "Error: ${response.errorBody()?.string()}"
                        searchResultListRV.visibility = View.INVISIBLE
                        errorMessageTV.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<YelpSearchResults>, t: Throwable) {
                    errorMessageTV.text = "Error: ${t.message}"
                    loadingIndicator.visibility = View.INVISIBLE
                    searchResultListRV.visibility = View.INVISIBLE
                    errorMessageTV.visibility = View.VISIBLE
                    Log.d("MainActivity", "Error making API call: ${t.message}")
                }
            })
    }
}