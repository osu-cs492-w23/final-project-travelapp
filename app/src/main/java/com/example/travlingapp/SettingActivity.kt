package com.example.travlingapp

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        sharedPreferences.apply {
            getString("unit",null)?.let {
                if (it == "km") {
                    km.visibility = View.VISIBLE
                } else {
                    mile.visibility = View.VISIBLE
                }
            }
            getString("rankingBy",null)?.let {
                if (it == "ratting") {
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
            shareEdit.putString("unit","km")
            shareEdit.apply()
        }
        mile_c.setOnClickListener {
            km.visibility = View.INVISIBLE
            mile.visibility = View.VISIBLE
            shareEdit.putString("unit","mile")
            shareEdit.apply()
        }


        ratting_c.setOnClickListener {
            ratting.visibility = View.VISIBLE
            review_count.visibility = View.INVISIBLE
            shareEdit.putString("rankingBy","ratting")
            shareEdit.apply()
        }
        rc_c.setOnClickListener {
            ratting.visibility = View.INVISIBLE
            review_count.visibility = View.VISIBLE
            shareEdit.putString("rankingBy","reviewCount")
            shareEdit.apply()
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        // 如果点击了返回按钮

        // 如果点击了返回按钮
        if (id == android.R.id.home) {
            // 关闭当前Activity
            finish()
            return true
        }

        return true
    }

}