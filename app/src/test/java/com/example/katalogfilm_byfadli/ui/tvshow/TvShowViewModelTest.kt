package com.example.katalogfilm_byfadli.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import com.example.katalogfilm_byfadli.data.source.MovieRepository
import com.example.katalogfilm_byfadli.utils.DataDummy
import com.example.katalogfilm_byfadli.utils.generateMovieEntities
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Result<List<MovieEntity>>>


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShowsData() = runBlocking {
        val dummyTvShows = DataDummy.loadTvShowsData().generateMovieEntities(12)

        `when`(movieRepository.getFavoritesTvShows(12)).thenReturn(Result.Success(dummyTvShows))
        viewModel.loadTvShowData()
        verify(movieRepository).getFavoritesTvShows(12)

        val moviesEntities = viewModel.getTvShowsData()
        assertThat(moviesEntities).isNotNull()
        assertThat((moviesEntities.value as Result.Success).data.size).isEqualTo(12)

        viewModel.getTvShowsData().observeForever(observer)
        verify(observer).onChanged(Result.Success(dummyTvShows))
    }
}