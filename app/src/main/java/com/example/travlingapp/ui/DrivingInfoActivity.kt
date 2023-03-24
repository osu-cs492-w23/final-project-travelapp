package com.example.travlingapp.ui
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.travlingapp.api.GoogleMapService
import com.example.travlingapp.data.DistanceResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.travlingapp.R

const val EXTRA_DRIVE_FROM = "DRIVING_INFO_FROM"
const val EXTRA_DRIVE_TO = "DRIVING_INFO_TO"

class DrivingInfoActivity : AppCompatActivity() {
    private val googleMapService = GoogleMapService.create()
    private val GoogleAPIKEY = "AIzaSyA1xiehRaRuaAgCxlbFb-V08NamxWGe-7s"
    private var results: DistanceResult? = null
    private var distance = ""
    private var time = ""
    private var originCity: String = "Corvallis"
    private var destCity: String = "Portland"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drive_info)

        if(intent != null && intent.hasExtra(EXTRA_DRIVE_FROM) && intent.hasExtra(EXTRA_DRIVE_TO)){
            originCity = intent.getSerializableExtra(EXTRA_DRIVE_FROM) as String
            destCity = intent.getSerializableExtra(EXTRA_DRIVE_TO) as String
        }

        googleMapService.loadDistanceMatrix(originCity, destCity, GoogleAPIKEY)
            .enqueue(object : Callback<DistanceResult> {
                override fun onResponse(
                    call: Call<DistanceResult>,
                    response: Response<DistanceResult>
                ) {

                    distance = response.body()!!.rows[0].elements[0].distance.text
                    time =  response.body()!!.rows[0].elements[0].duration.text
                    findViewById<TextView>(R.id.tv_driving_distance).text = distance
                    findViewById<TextView>(R.id.tv_driving_time).text = time
                    findViewById<TextView>(R.id.tv_origin).text = originCity
                    findViewById<TextView>(R.id.tv_destination).text = destCity

                }

                override fun onFailure(call: Call<DistanceResult>, t: Throwable) {
                    Log.d("MainActivity", "fail $t")
                }
            })
        }



        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.driving_info_detail,menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val geocoder = Geocoder(this)
            val coordListFrom = geocoder.getFromLocationName(originCity, 1)!![0]
            val coordListTo = geocoder.getFromLocationName(destCity, 1)!![0]
            if(item.itemId == R.id.action_map){
                val navigationUrl = getString(R.string.geo_uri)
                val geoUri = String.format(navigationUrl,
                    coordListFrom.latitude,
                    coordListFrom.longitude,
                    coordListTo.latitude,
                    coordListTo.longitude)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                startActivity(intent)
            }
            return super.onOptionsItemSelected(item)
        }

    }
