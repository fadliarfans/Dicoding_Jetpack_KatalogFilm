package com.example.katalogfilm_byfadli.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import com.example.katalogfilm_byfadli.data.source.MovieRepository
import com.example.katalogfilm_byfadli.utils.DataDummy
import com.example.katalogfilm_byfadli.utils.generateMovieEntities
import com.example.katalogfilm_byfadli.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMoviesData() = runBlocking {
        val dummyMovies = DataDummy.loadMoviesData().generateMovieEntities(10)

        `when`(movieRepository.getRecommendationsMovies(10)).thenReturn(MutableLiveData(Resource.success(dummyMovies)))
        viewModel.loadMovieOrTvShowDataData(false)
        verify(movieRepository).getRecommendationsMovies(10)

        val moviesEntities = viewModel.getMovieOrTvShowData()
        assertThat(moviesEntities).isNotNull()
        assertThat(moviesEntities.value?.data?.size?:0).isEqualTo(10)

        viewModel.getMovieOrTvShowData().observeForever(observer)
        verify(observer).onChanged(Resource.success(dummyMovies))
    }

    @Test
    fun getTvShowsData() = runBlocking {
        val dummyTvShows = DataDummy.loadTvShowsData().generateMovieEntities(12)

        `when`(movieRepository.getRecommendationsTvShows(10)).thenReturn(MutableLiveData(Resource.success(dummyTvShows)))
        viewModel.loadMovieOrTvShowDataData(true)
        verify(movieRepository).getRecommendationsTvShows(10)

        val moviesEntities = viewModel.getMovieOrTvShowData()
        assertThat(moviesEntities).isNotNull()
        assertThat(moviesEntities.value?.data?.size?:0).isEqualTo(12)

        viewModel.getMovieOrTvShowData().observeForever(observer)
        verify(observer).onChanged(Resource.success(dummyTvShows))
    }
}