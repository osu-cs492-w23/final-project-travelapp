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
    @Json val location: Location
    ):java.io.Serializable

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "display_address") val address: Array<String>,
):java.io.Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Location
        if (!address.contentEquals(other.address)) return false
        return true
    }

    override fun hashCode(): Int {
        return address.contentHashCode()
    }
}