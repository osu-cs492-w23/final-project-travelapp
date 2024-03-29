package com.example.travlingapp.ui

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.travlingapp.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        sharedPreferences.apply {
            getString("unit",null)?.let {
                if (it == "metric") {
                    km.visibility = View.VISIBLE
                } else {
                    mile.visibility = View.VISIBLE
                }
            }
            getString("rankingBy",null)?.let {
                if (it == "rating") {
                    ratting.visibility = View.VISIBLE
                } else {
                    review_count.visibility = View.VISIBLE
                }
            }

        }

        val shareEdit = sharedPreferences.edit()
        km_c.setOnClickListener {
            km.visibility = View.VISIBLE
            mile.visibility = View.INVISIBLE
            shareEdit.putString("unit","metric")
            shareEdit.apply()
        }
        mile_c.setOnClickListener {
            km.visibility = View.INVISIBLE
            mile.visibility = View.VISIBLE
            shareEdit.putString("unit","imperial")
            shareEdit.apply()
        }


        ratting_c.setOnClickListener {
            ratting.visibility = View.VISIBLE
            review_count.visibility = View.INVISIBLE
            shareEdit.putString("rankingBy","rating")
            shareEdit.apply()
        }
        rc_c.setOnClickListener {
            ratting.visibility = View.INVISIBLE
            review_count.visibility = View.VISIBLE
            shareEdit.putString("rankingBy","review_count")
            shareEdit.apply()
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }

        return true
    }

}