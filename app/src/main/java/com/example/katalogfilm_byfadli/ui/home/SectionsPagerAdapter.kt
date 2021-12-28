package com.example.katalogfilm_byfadli.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.katalogfilm_byfadli.ui.movie.MovieFragment
import com.example.katalogfilm_byfadli.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MovieFragment()
        1 -> TvShowFragment()
        else -> Fragment()
    }
}