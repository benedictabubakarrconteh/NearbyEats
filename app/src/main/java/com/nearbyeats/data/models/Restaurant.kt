package com.nearbyeats.data.models

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("name")
    val name: String,
    @SerializedName("vicinity")
    val vicinity: String,
    @SerializedName("rating")
    val rating: Double = 0.0
)

data class PlacesResponse(
    @SerializedName("results")
    val results: List<Restaurant>,
    @SerializedName("status")
    val status: String
)
