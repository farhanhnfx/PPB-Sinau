package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalArgumentException

class TabAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    val pages = arrayOf<Fragment>(HomeFragment(), MaterialFragment(), QuizFragment())
    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }
}