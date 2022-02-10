package com.example.katalogfilm_byfadli.ui.movieFavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.example.katalogfilm_byfadli.data.source.MovieRepository
import com.example.katalogfilm_byfadli.utils.DataDummy
import com.example.katalogfilm_byfadli.utils.generateMovieEntities
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {
    private lateinit var viewModel: MovieFavoriteViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MovieFavoriteViewModel(movieRepository)
    }

    @Test
    fun getFavoritesMovies() {
        val dummyMovies = DataDummy.loadMoviesData().generateMovieEntities(10)
        val pagingData = PagingData.from(dummyMovies)

        `when`(movieRepository.getFavoritesMovies(null))
            .thenReturn(MutableLiveData(pagingData))

        val moviesPagingData = viewModel.loadMovieOrTvShowData(false)
        Truth.assertThat(moviesPagingData).isNotNull()
    }

    @Test
    fun getFavoritesTvShows() {
        val dummyTvShows = DataDummy.loadTvShowsData().generateMovieEntities(12)
        val pagingData = PagingData.from(dummyTvShows)

        `when`(movieRepository.getFavoritesTvShows(null))
            .thenReturn(MutableLiveData(pagingData))

        val moviesPagingData = viewModel.loadMovieOrTvShowData(false)
        Truth.assertThat(moviesPagingData).isNotNull()
    }

}