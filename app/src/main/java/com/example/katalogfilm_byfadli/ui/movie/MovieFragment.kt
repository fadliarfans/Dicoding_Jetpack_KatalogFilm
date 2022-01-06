package com.example.katalogfilm_byfadli.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.katalogfilm_byfadli.databinding.FragmentMovieBinding
import com.example.katalogfilm_byfadli.ui.home.HomeViewModel


class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            initViewModel()
            initRecycleView()
        }
    }

    private fun initRecycleView() {
        val movieAdapter = MovieAdapter(viewModel.getMoviesData().value ?: listOf())
        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MovieViewModel::class.java]

        viewModel.loadMoviesData()

        homeViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]

        homeViewModel.getSearchData().observe(viewLifecycleOwner, { query ->
            viewModel.loadSearchedMoviesData(query ?: "")
            binding.rvMovie.adapter = MovieAdapter(viewModel.getMoviesData().value ?: listOf())
        })
    }
}