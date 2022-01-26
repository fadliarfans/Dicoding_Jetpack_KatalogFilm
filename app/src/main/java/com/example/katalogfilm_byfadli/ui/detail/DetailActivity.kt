package com.example.katalogfilm_byfadli.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.katalogfilm_byfadli.R
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.data.Result
import com.example.katalogfilm_byfadli.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initiateData()
        initiateObserver()
    }

    private fun initiateData() {
        val extras = intent.extras
        if (extras != null) {
            val data: MovieEntity? = extras.getParcelable(EXTRA_DATA)
            viewModel.setDetailMovieOrTvShowData(data)
        }
    }

    private fun initiateObserver() {
        viewModel.getDetailMovieOrTvShowData().observe(this, {
            when (it) {
                is Result.Success -> {
                    binding.detailError.layoutError.visibility = View.GONE
                    showDetail(it.data)
                    setupAppBar(it.data)
                }
                else -> {
                    binding.detailError.layoutError.visibility = View.VISIBLE
                    binding.detailError.tvErrorMessage.text =
                        (it as Result.Error).exception.localizedMessage
                }
            }
        })
    }

    private fun showDetail(data: MovieEntity) {
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${data.backdropPath}")
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading_2)
                    .error(R.drawable.ic_error)
            ).transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivBackdrop)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${data.posterPath}")
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            ).transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivPoster)
        with(binding) {
            tvTitle.text = data.title
            tvOverviewValue.text = data.overview
            tvVoteAverageValue.text = data.voteAverage.toString()
            tvVoteTotalValue.text = data.voteCount.toString()
            tvVoteTotalValue.text = data.voteCount.toString()
            tvGenre.text = data.genreIds
            tvFirstAirDateValue.text =
                data.date
        }
    }

    private fun setupAppBar(data: MovieEntity) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = if (data.isTv == true) {
            "Detail TvShow"
        } else {
            "Detail Movie"
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}