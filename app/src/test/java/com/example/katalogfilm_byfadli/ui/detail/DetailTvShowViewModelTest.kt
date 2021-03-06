package com.example.katalogfilm_byfadli.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.katalogfilm_byfadli.data.source.MovieRepository
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import com.example.katalogfilm_byfadli.utils.DataDummy
import com.example.katalogfilm_byfadli.utils.generateMovieEntities
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun insertFavorite() {
        val movieEntity =  DataDummy.loadTvShowsData().generateMovieEntities(12)[10]
        viewModel.setDetailMovieOrTvShowData(
            movieEntity
        )
        viewModel.insertFavorite()
        assertThat(viewModel.getFavoriteState().value).isTrue()
    }

    @Test
    fun deleteFavorite() {
        val movieEntity =  DataDummy.loadTvShowsData().generateMovieEntities(12)[10]
        viewModel.setDetailMovieOrTvShowData(
            movieEntity
        )
        viewModel.deleteFavorite()
        assertThat(viewModel.getFavoriteState().value).isFalse()
    }

    @Test
    fun getDetailTvShow() {
        viewModel.setDetailMovieOrTvShowData(
            DataDummy.loadTvShowsData().generateMovieEntities(12)[10]
        )
        val detailTvShowLiveData = viewModel.getDetailMovieOrTvShowData()
        val detailTvShow = detailTvShowLiveData.value

        assertThat(detailTvShow).isNotNull()
        // id test
        assertThat(detailTvShow?.id).isEqualTo(77169)
        // name test
        assertThat(detailTvShow?.title).isEqualTo("Cobra Kai")
        // genre test
        assertThat(
            detailTvShow?.genreIds
        ).isEqualTo("Action & Adventure, Drama")
        // overview test
        assertThat(
            detailTvShow?.overview
        ).isEqualTo("This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.")
        // release date test
        assertThat(detailTvShow?.date).isEqualTo("02 Mei 2018")
        // poster path test
        assertThat(detailTvShow?.posterPath).isEqualTo("/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg")
        // backdrop path test
        assertThat(detailTvShow?.backdropPath).isEqualTo("/35SS0nlBhu28cSe7TiO3ZiywZhl.jpg")
        // vote average test
        assertThat(detailTvShow?.voteAverage).isEqualTo(8.2)
        // vote count test
        assertThat(detailTvShow?.voteCount).isEqualTo(4168)

        viewModel.getDetailMovieOrTvShowData().observeForever(observer)
        verify(observer).onChanged(
            DataDummy.loadTvShowsData().generateMovieEntities(12)[10]
        )
    }

    @Test
    fun getDetailMovie() {
        viewModel.setDetailMovieOrTvShowData(
            DataDummy.loadMoviesData().generateMovieEntities(10)[1]
        )
        val detailMovieLiveData = viewModel.getDetailMovieOrTvShowData()
        val detailMovie = detailMovieLiveData.value

        assertThat(detailMovie).isNotNull()
        // id test
        assertThat(detailMovie?.id).isEqualTo(728526)
        // name test
        assertThat(detailMovie?.title).isEqualTo("Encounter")
        // genre test
        assertThat(
            detailMovie?.genreIds
        ).isEqualTo("Adventure, Drama")
        // overview test
        assertThat(
            detailMovie?.overview
        ).isEqualTo("A decorated Marine goes on a rescue mission to save his two young sons from an unhuman threat. As their journey takes them in increasingly dangerous directions, the boys will need to leave their childhoods behind.")
        // release date test
        assertThat(detailMovie?.date).isEqualTo("03 Desember 2021")
        // poster path test
        assertThat(detailMovie?.posterPath).isEqualTo("/tUkY0WxffPZ9PoyC62PIyyUMGnt.jpg")
        // backdrop path test
        assertThat(detailMovie?.backdropPath).isEqualTo("/zlj0zHo67xXoj7hvwGtaKRkSdBV.jpg")
        // vote average test
        assertThat(detailMovie?.voteAverage).isEqualTo(6.4)
        // vote count test
        assertThat(detailMovie?.voteCount).isEqualTo(209)

        viewModel.getDetailMovieOrTvShowData().observeForever(observer)
        verify(observer).onChanged(
            DataDummy.loadMoviesData().generateMovieEntities(10)[1]
        )
    }
}