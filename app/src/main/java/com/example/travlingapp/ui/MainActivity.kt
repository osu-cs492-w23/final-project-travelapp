package com.example.travlingapp.ui

import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.travlingapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val units = sharedPreferences.getString("unit",null)
        val rankingBy = sharedPreferences.getString("rankingBy",null)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
                return true
            }
        }
        return true
    }
}