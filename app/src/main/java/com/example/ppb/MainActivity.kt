package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterRestaurant = RestaurantAdapter(generateRestaurants()) {
            restaurant ->
            Toast.makeText(this@MainActivity, "${restaurant.name} clicked!", Toast.LENGTH_SHORT).show()
        }
        with(binding) {
            rvRestaurants.apply {
                adapter = adapterRestaurant
                layoutManager = LinearLayoutManager(this@MainActivity)
//                layoutManager = GridLayoutManager(this@MainActivity, 2)
            }
        }
    }

    private fun generateRestaurants(): List<Restaurant> {
        return  listOf(
            Restaurant(name="McDonalds", type="Western"),
            Restaurant(name="Bornga", type="Korean"),
            Restaurant(name="Sushi Tei", type="Japanese"),
            Restaurant(name="Mas Kobis", type="Javanese"),
            Restaurant(name="Leko", type="Indonesian")
        )
    }
}