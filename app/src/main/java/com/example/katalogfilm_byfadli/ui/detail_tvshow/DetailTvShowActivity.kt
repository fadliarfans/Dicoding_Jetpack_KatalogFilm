package com.example.katalogfilm_byfadli.ui.detail_tvshow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.katalogfilm_byfadli.databinding.ActivityDetailTvShowBinding


class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}