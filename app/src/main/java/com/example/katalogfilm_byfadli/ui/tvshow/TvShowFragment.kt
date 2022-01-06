package com.example.katalogfilm_byfadli.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.katalogfilm_byfadli.databinding.FragmentTvShowBinding
import com.example.katalogfilm_byfadli.ui.home.HomeViewModel


class TvShowFragment : Fragment() {
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            initViewModel()
            initRecycleView()
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[TvShowViewModel::class.java]

        viewModel.loadTvShowsData()

        homeViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]

        homeViewModel.getSearchData().observe(viewLifecycleOwner, {
            viewModel.loadSearchedTvShowsData(it ?: "")
            binding.rvTvShow.adapter = TvShowAdapter(viewModel.getTvShowsData().value ?: listOf())
        })
    }

    private fun initRecycleView() {
        val tvShowAdapter = TvShowAdapter(viewModel.getTvShowsData().value ?: listOf())
        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }
}