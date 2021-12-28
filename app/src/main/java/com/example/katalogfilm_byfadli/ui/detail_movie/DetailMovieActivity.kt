package com.example.katalogfilm_byfadli.ui.detail_movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.katalogfilm_byfadli.databinding.ActivityDetailMovieBinding


class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object{
        const val EXTRA_COURSE = "extra_course"
    }
}