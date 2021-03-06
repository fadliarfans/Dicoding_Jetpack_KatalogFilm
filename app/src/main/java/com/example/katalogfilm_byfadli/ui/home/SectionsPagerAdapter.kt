package com.example.katalogfilm_byfadli.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.katalogfilm_byfadli.ui.movie.MovieFragment

class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MovieFragment(false)
        1 -> MovieFragment(true)
        else -> Fragment()
    }
}