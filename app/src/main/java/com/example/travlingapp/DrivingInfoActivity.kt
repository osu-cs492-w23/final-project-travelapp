package com.example.travlingapp
import android.content.Intent
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

const val EXTRA_DRIVE = "DRIVING_INFO"

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
        if(item.itemId == R.id.action_map){
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:44.564568,-123.262047")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    }
