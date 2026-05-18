package com.nearbyeats.data.api

import com.nearbyeats.data.models.PlacesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesService {
    @GET("maps/api/place/nearbysearch/json")
    fun getRestaurants(
        @Query("location") location: String,
        @Query("radius") radius: Int = 2000,
        @Query("type") type: String = "restaurant",
        @Query("key") apiKey: String
    ): Call<PlacesResponse>
}
