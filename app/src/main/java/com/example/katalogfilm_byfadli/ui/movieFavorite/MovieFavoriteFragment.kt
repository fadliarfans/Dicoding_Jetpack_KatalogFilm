package com.example.katalogfilm_byfadli.ui.movieFavorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.katalogfilm_byfadli.databinding.FragmentMovieFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieFavoriteFragment(private val isTv: Boolean) : Fragment() {

    private lateinit var binding: FragmentMovieFavoriteBinding

    private val viewModel: MovieFavoriteViewModel by viewModels()
    private val movieFavoriteAdapter: MovieFavoriteAdapter by lazy {
        MovieFavoriteAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateObserver()
        initiateUI()
    }

    private fun initiateUI() {
        movieFavoriteAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && movieFavoriteAdapter.itemCount < 1) {
                binding.rvMovieFavorite.visibility = View.GONE
                binding.empty.layoutEmpty.visibility = View.VISIBLE
            } else {
                binding.rvMovieFavorite.visibility = View.VISIBLE
                binding.empty.layoutEmpty.visibility = View.GONE
            }
        }
        with(binding.rvMovieFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieFavoriteAdapter
        }
    }


    private fun initiateObserver() {
        viewModel.loadMovieOrTvShowData(isTv).observe(viewLifecycleOwner) {
            lifecycleScope.launchWhenCreated {
                movieFavoriteAdapter.submitData(it)
            }
        }
    }
}