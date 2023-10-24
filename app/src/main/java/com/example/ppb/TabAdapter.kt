package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalArgumentException

class TabAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    private val pages = arrayOf<Fragment>(RegisterFragment(), LoginFragment())
    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }
}