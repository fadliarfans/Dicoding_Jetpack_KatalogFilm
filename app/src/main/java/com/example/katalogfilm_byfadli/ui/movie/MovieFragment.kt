package com.example.katalogfilm_byfadli.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.katalogfilm_byfadli.databinding.FragmentMovieBinding
import com.example.katalogfilm_byfadli.ui.home.HomeViewModel
import com.example.katalogfilm_byfadli.vo.Status
import com.example.katalogfilm_byfadli.vo.Status.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment(private val isTv: Boolean) : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateObserver()
        initiateData()
        initiateUI()
    }

    private fun initiateData() {
        viewModel.loadMovieOrTvShowDataData(isTv = isTv)
    }

    private fun initiateUI() {
        showRecycleView()
        setListenerToSwipeRefreshLayout()
    }

    private fun setListenerToSwipeRefreshLayout() {
        with(binding) {
            srLayout.setOnRefreshListener {
                initiateData()
            }
        }
    }

    private fun initiateObserver() {
        viewModel.getData().observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    binding.rvMovie.visibility = View.VISIBLE
                    binding.progress.visibility = View.GONE
                    binding.error.layoutError.visibility = View.GONE
                    binding.srLayout.isRefreshing = false
                    if (it.data?.isEmpty() != false) {
                        binding.empty.layoutEmpty.visibility = View.VISIBLE
                        movieAdapter.setList(mutableListOf())
                    } else {
                        binding.empty.layoutEmpty.visibility = View.GONE
                        movieAdapter.setList(it.data)
                    }
                }
                ERROR -> {
                    binding.rvMovie.visibility = View.GONE
                    binding.error.layoutError.visibility = View.VISIBLE
                    binding.empty.layoutEmpty.visibility = View.GONE
                    binding.progress.visibility = View.GONE
                    binding.error.tvErrorMessage.text = it.message
                    binding.srLayout.isRefreshing = false
                }
                else -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.empty.layoutEmpty.visibility = View.GONE
                    binding.error.layoutError.visibility = View.GONE
                }
            }
        }
        homeViewModel.getSearchData().observe(viewLifecycleOwner) {
            viewModel.loadSearchedData(it ?: "")
        }
    }

    private fun showRecycleView() {
        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = movieAdapter
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}