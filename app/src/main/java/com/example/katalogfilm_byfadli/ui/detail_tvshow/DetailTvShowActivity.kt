package com.example.katalogfilm_byfadli.ui.detail_tvshow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.katalogfilm_byfadli.R
import com.example.katalogfilm_byfadli.data.TvShowEntity
import com.example.katalogfilm_byfadli.databinding.ActivityDetailTvShowBinding

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvShowBinding
    private var tvShow: TvShowEntity? = null
    private lateinit var viewModel: DetailTvShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAppBar()
        initViewModel()
        getTvShow()
        bindDataToUi()
    }

    private fun bindDataToUi() {
        if (tvShow != null) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${tvShow?.backdropPath}")
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                ).transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivBackdrop)

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${tvShow?.posterPath}")
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                ).transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivPoster)
            with(binding) {
                tvTitle.text = tvShow?.name
                tvOverviewValue.text = tvShow?.overview
                tvVoteAverageValue.text = tvShow?.voteAverage.toString()
                tvPopularityValue.text = tvShow?.popularity?.toInt().toString()
                tvVoteTotalValue.text = tvShow?.voteCount.toString()
                tvGenre.text = viewModel.generateGenre(tvShow?.genreIds)
                tvFirstAirDateValue.text =
                    viewModel.changeDateFormat(tvShow?.firstAirDate ?: "0000-00-00")
                tvOriginCountryValue.text = tvShow?.originCountry.toString()
                    .subSequence(1, tvShow?.originCountry.toString().length - 1)
            }
        }
    }

    private fun getTvShow() {
        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_DATA)
            tvShow = viewModel.getDetailTvShow(tvShowId)
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailTvShowViewModel::class.java]
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