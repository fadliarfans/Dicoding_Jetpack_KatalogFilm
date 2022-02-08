package com.example.katalogfilm_byfadli.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.katalogfilm_byfadli.ui.movie.MovieFragment
import com.example.katalogfilm_byfadli.ui.movieFavorite.MovieFavoriteFragment

class SectionPagerAdapterFavorite(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MovieFavoriteFragment(false)
        1 -> MovieFavoriteFragment(true)
        else -> Fragment()
    }
}