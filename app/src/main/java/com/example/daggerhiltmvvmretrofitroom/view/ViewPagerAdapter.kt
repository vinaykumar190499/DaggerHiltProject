package com.example.daggerhiltmvvmretrofitroom.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2 // Number of fragments

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentOne()
            1 -> FragmentTwo()
            else -> throw IllegalStateException("Invalid position $position")
        }
    }
}