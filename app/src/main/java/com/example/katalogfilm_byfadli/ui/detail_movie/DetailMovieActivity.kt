package com.example.katalogfilm_byfadli.ui.detail_movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.katalogfilm_byfadli.R
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.databinding.ActivityDetailMovieBinding


class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private var movie: MovieEntity? = null
    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAppBar()
        initViewModel()
        getMovie()
        bindDataToUi()
    }

    private fun bindDataToUi() {
        if (movie != null) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${movie?.backdropPath}")
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                ).transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivBackdrop)

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                ).transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivPoster)
            with(binding) {
                tvTitle.text = movie?.title
                tvOverviewValue.text = movie?.overview
                tvVoteAverageValue.text = movie?.voteAverage.toString()
                tvPopularityValue.text = movie?.popularity?.toInt().toString()
                tvVoteTotalValue.text = movie?.voteCount.toString()
                tvYear.text = movie?.releaseDate.toString().subSequence(0, 4)
                tvGenre.text = viewModel.generateGenre(movie?.genreIds)
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]
    }

    private fun getMovie() {
        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_DATA)
            movie = viewModel.getDetailMovie(movieId)
        }
    }

    private fun initAppBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Detail Movie"
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}