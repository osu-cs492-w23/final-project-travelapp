package com.example.travlingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.travlingapp.R
import com.google.android.material.progressindicator.CircularProgressIndicator

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputBoxFrom: EditText = findViewById(R.id.input_box_from)
        val inputBoxTo: EditText = findViewById(R.id.input_box_to)

        val searchBtn: Button = findViewById(R.id.btn_search)


        searchBtn.setOnClickListener {
            val originCity = inputBoxFrom.text.toString()
            val destinationCity = inputBoxTo.text.toString()
            Log.d(TAG, "origin city: $originCity, destination city: $destinationCity")
        }

    }
}