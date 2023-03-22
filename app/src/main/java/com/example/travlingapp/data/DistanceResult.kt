package com.example.travlingapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DistanceResult(
    val rows: List<Rows>
): java.io.Serializable

@JsonClass(generateAdapter = true)
data class Rows(
    val elements: List<Elements>
): java.io.Serializable

@JsonClass(generateAdapter = true)
data class Elements(
    val distance: Distance,
    val duration: Duration
): java.io.Serializable

@JsonClass(generateAdapter = true)
data class Distance(
    val text: String,
    val value: Int
): java.io.Serializable

@JsonClass(generateAdapter = true)
data class Duration(
    val text: String,
    val value: Int
)
