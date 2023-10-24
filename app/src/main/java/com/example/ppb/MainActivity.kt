package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.ppb.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewPager: ViewPager2

    companion object {
        var USERNAME: String = ""
        var PASSWORD: String = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = TabAdapter(this@MainActivity)
        supportActionBar?.hide()

        with (binding) {
//            viewPager.adapter = TabAdapter(this@MainActivity)
//            viewPagerMain = viewPager
            TabLayoutMediator(tabLayout, viewPager) {
                tab, position ->
                tab.text = when(position) {
                    0 -> "Register" // judul tab
                    1 -> "Login"
                    else -> ""
                }
            }.attach()
        }
    }
}