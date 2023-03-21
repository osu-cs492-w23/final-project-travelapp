package com.example.travlingapp.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class YelpSearchResults(
    val business: List<YelpRepo>
)
