package com.example.katalogfilm_byfadli.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.katalogfilm_byfadli.R
import com.example.katalogfilm_byfadli.databinding.FragmentTvShowBinding


class TvShowFragment : Fragment() {
    private lateinit var binding:FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}