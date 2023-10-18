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
    private lateinit var viewPagerMain: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        viewPager = findViewById(R.id.view_pager)
//        viewPager.adapter = TabAdapter(this@MainActivity)
        title = "Dashboard"
        supportActionBar?.hide()

        with (binding) {
            viewPager.adapter = TabAdapter(this@MainActivity)
            viewPagerMain = viewPager
            TabLayoutMediator(tabLayout, viewPager) {
                tab, position ->
                tab.text = when(position) {
                    0 -> "Home" // judul tab
                    1 -> "Material"
                    2 -> "Quiz"
                    else -> ""
                }
            }.attach()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_home -> {
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                viewPagerMain.setCurrentItem(0)
                true
            }
            R.id.action_material -> {
                Toast.makeText(this, "Material", Toast.LENGTH_SHORT).show()
                viewPagerMain.setCurrentItem(1)
                true
            }
            R.id.action_quiz -> {
                Toast.makeText(this, "Quiz", Toast.LENGTH_SHORT).show()
                viewPagerMain.setCurrentItem(2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}