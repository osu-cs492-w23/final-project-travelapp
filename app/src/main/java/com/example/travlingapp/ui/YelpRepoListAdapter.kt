package com.example.travlingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travlingapp.R
import com.example.travlingapp.data.YelpRepo

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
        private val nameTV: TextView = itemView.findViewById(R.id.tv_name)
        private var currentYelpRepo: YelpRepo? = null

        fun bind(yelpRepo: YelpRepo){
            currentYelpRepo = yelpRepo
            nameTV.text = yelpRepo.name
        }
    }
}