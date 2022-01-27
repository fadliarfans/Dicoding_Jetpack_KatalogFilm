package com.example.katalogfilm_byfadli.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.katalogfilm_byfadli.data.Result
import com.example.katalogfilm_byfadli.databinding.FragmentTvShowBinding
import com.example.katalogfilm_byfadli.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvShowViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    private val tvShowAdapter: TvShowAdapter by lazy {
        TvShowAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateObserver()
        initiateData()
        initiateUI()
    }

    private fun initiateData() {
        viewModel.loadTvShowData()
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
        viewModel.getTvShowsData().observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success -> {
                    binding.rvTvShow.visibility = View.VISIBLE
                    binding.progress.visibility = View.GONE
                    binding.error.layoutError.visibility = View.GONE
                    binding.srLayout.isRefreshing = false
                    if (it.data.isEmpty()) {
                        binding.empty.layoutEmpty.visibility = View.VISIBLE
                        tvShowAdapter.setList(it.data)
                    } else {
                        binding.empty.layoutEmpty.visibility = View.GONE
                        tvShowAdapter.setList(it.data)
                    }
                }
                is Result.Error -> {
                    binding.rvTvShow.visibility = View.GONE
                    binding.error.layoutError.visibility = View.VISIBLE
                    binding.empty.layoutEmpty.visibility = View.GONE
                    binding.progress.visibility = View.GONE
                    binding.error.tvErrorMessage.text = it.exception.localizedMessage
                    binding.srLayout.isRefreshing = false
                }
                else -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.empty.layoutEmpty.visibility = View.GONE
                    binding.error.layoutError.visibility = View.GONE
                }
            }
        })
        homeViewModel.getSearchData().observe(viewLifecycleOwner, {
            viewModel.loadSearchedTvShowData(it ?: "")
        })
    }

    private fun showRecycleView() {
        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = tvShowAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}