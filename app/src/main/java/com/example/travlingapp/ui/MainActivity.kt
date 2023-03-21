package com.example.travlingapp.ui

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBoxET: EditText = findViewById(R.id.et_search_box)
        val searchBtn: Button = findViewById(R.id.btn_search)


        searchBtn.setOnClickListener {
            val location = searchBoxET.text.toString()
            val intent = Intent(this, YelpRepoListActivity::class.java)
            if(!TextUtils.isEmpty(location)){
                intent.putExtra(EXTRA_YELP_REPO, location)
                startActivity(intent)
            }
        }
    }

}