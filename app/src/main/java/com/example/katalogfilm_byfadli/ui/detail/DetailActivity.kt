package com.example.katalogfilm_byfadli.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.katalogfilm_byfadli.R
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private var data: MovieEntity? = null
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAppBar()
        initViewModel()
        getData()
        bindDataToUi()
    }

    private fun bindDataToUi() {
        if (data != null) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${data?.backdropPath}")
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                ).transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivBackdrop)

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${data?.posterPath}")
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                ).transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivPoster)
            with(binding) {
                tvTitle.text = data?.title
                tvOverviewValue.text = data?.overview
                tvVoteAverageValue.text = data?.voteAverage.toString()
                tvPopularityValue.text = data?.popularity?.toInt().toString()
                tvVoteTotalValue.text = data?.voteCount.toString()
                tvVoteTotalValue.text = data?.voteCount.toString()
                tvGenre.text = viewModel.generateGenre(data?.genreIds)
              tvFirstAirDateValue.text =
                  viewModel.changeDateFormat(data?.releaseDate ?: "0000-00-00")
            }
        }
    }

    private fun getData() {
        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_DATA)
            //data = viewModel.getDetailTvShow(tvShowId)
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]
    }

    private fun initAppBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Detail Tv Show"
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}