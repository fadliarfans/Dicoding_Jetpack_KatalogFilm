package com.example.katalogfilm_byfadli.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.katalogfilm_byfadli.R
import com.example.katalogfilm_byfadli.databinding.FragmentMovieBinding
import com.example.katalogfilm_byfadli.utils.DataDummy


class MovieFragment : Fragment() {

    private lateinit var binding:FragmentMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val movieAdapter = MovieAdapter(DataDummy.loadDataMovies())
            with(binding.rvMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}