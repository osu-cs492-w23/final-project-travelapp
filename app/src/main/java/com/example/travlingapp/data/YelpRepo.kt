package com.example.travlingapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class YelpRepo(
    @Json val name: String,
    @Json val image_url: String,
    @Json val url: String,
    @Json val rating: Float,
    @Json val phone: String,
    @Json val location: List<Address>
    ):java.io.Serializable


@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "display_address") val address: String,
):java.io.Serializable