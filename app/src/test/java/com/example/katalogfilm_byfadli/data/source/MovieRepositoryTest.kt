package com.example.katalogfilm_byfadli.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.katalogfilm_byfadli.data.source.remote.RemoteDataSource
import com.example.katalogfilm_byfadli.utils.DataDummy
import com.example.katalogfilm_byfadli.utils.LiveDataTestUtil
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Rule

import org.junit.Test
import org.mockito.Mockito.*

class MovieRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val responseMovie = DataDummy.loadMoviesData()
    private val responseTvShow = DataDummy.loadTvShowsData()

    @Test
    fun getFavoritesMovies(): Unit = runBlocking{
        `when`(remote.getFavoritesMovies()).thenReturn(responseMovie)
        val moviesEntities = LiveDataTestUtil.getValue(MutableLiveData((movieRepository.getFavoritesMovies(10) as Result.Success).data))
        verify(remote).getFavoritesMovies()
        assertThat(moviesEntities).isNotNull()
        assertThat(responseMovie.size).isEqualTo(moviesEntities.size)
    }

    @Test
    fun getFavoritesTvShows() = runBlocking{
        `when`(remote.getFavoritesTvShows()).thenReturn(responseTvShow)
        val tvShowEntities = LiveDataTestUtil.getValue(MutableLiveData((movieRepository.getFavoritesTvShows(12) as Result.Success).data))
        verify(remote).getFavoritesTvShows()
        assertThat(tvShowEntities).isNotNull()
        assertThat(responseTvShow.size).isEqualTo(tvShowEntities.size)
    }
}