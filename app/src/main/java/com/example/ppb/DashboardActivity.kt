package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.ppb.databinding.ActivityDashboardBinding
import com.example.ppb.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Dashboard"
        binding.txtWelcome.setText("Welcome, ${MainActivity.USERNAME}!")
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}