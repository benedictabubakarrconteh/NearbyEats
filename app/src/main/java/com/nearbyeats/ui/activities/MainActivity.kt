package com.nearbyeats.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nearbyeats.data.api.RetrofitClient
import com.nearbyeats.data.models.PlacesResponse
import com.nearbyeats.databinding.ActivityMainBinding
import com.nearbyeats.ui.adapters.RestaurantAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiKey = "YOUR_GOOGLE_API_KEY"
    private val defaultLocation = "8.4657,-13.2317"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchRestaurants(defaultLocation)
    }

    private fun setupRecyclerView() {
        binding.restaurantRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun fetchRestaurants(location: String) {
        binding.progressBar.visibility = android.view.View.VISIBLE

        val placesService = RetrofitClient.getPlacesService()
        placesService.getRestaurants(location, apiKey = apiKey)
            .enqueue(object : Callback<PlacesResponse> {
                override fun onResponse(call: Call<PlacesResponse>, response: Response<PlacesResponse>) {
                    binding.progressBar.visibility = android.view.View.GONE
                    val data = response.body()?.results ?: emptyList()
                    binding.restaurantRecyclerView.adapter = RestaurantAdapter(data) { restaurant ->
                        openRestaurantLocation(restaurant.name)
                    }
                }

                override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                    binding.progressBar.visibility = android.view.View.GONE
                    showError("Error: ${t.message}")
                }
            })
    }

    private fun openRestaurantLocation(restaurantName: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=$restaurantName"))
        startActivity(intent)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
