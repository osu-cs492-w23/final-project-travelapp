package com.example.travlingapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travlingapp.R
import com.example.travlingapp.data.YelpRepo
import com.bumptech.glide.Glide


class YelpRepoListAdapter : RecyclerView.Adapter<YelpRepoListAdapter.YelpRepoViewHolder>(){
    var yelpRepoList = listOf<YelpRepo>()

    fun updateRepoList(newRepoList: List<YelpRepo>?){
        yelpRepoList = newRepoList ?: listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YelpRepoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.yelp_repo_list_item, parent, false)
        return YelpRepoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: YelpRepoViewHolder, position: Int) {
        holder.bind(yelpRepoList[position])
    }

    override fun getItemCount() = yelpRepoList.size

    class YelpRepoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val imageIV: ImageView = itemView.findViewById(R.id.iv_yelp)
        private val nameTV: TextView = itemView.findViewById(R.id.tv_name)
        private val ratingTV: TextView = itemView.findViewById(R.id.tv_rating)
        private val locationTV: TextView = itemView.findViewById(R.id.tv_location)
        private val phoneTV: TextView = itemView.findViewById(R.id.tv_phone)

        private var currentYelpRepo: YelpRepo? = null

        fun bind(yelpRepo: YelpRepo){
            currentYelpRepo = yelpRepo
            val ctx = itemView.context

            nameTV.text = ctx.getString(R.string.yelp_name, yelpRepo.name)
            ratingTV.text = ctx.getString(R.string.yelp_rating, yelpRepo.rating)
            locationTV.text = ctx.getString(R.string.yelp_location, yelpRepo.location.address[0])
            phoneTV.text = ctx.getString(R.string.yelp_phone, yelpRepo.phone)

            Log.d("MainActivity", "url---->${yelpRepo.image_url}")
            Glide.with(ctx)
                .load(yelpRepo.image_url)
                .centerCrop()
                .placeholder(R.drawable.baseline_image_24)
                .override(400,300)
                .into(imageIV)
        }
    }
}