package com.example.katalogfilm_byfadli.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.example.katalogfilm_byfadli.data.source.local.LocalDataSource
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import com.example.katalogfilm_byfadli.data.source.remote.ApiResponse
import com.example.katalogfilm_byfadli.data.source.remote.RemoteDataSource
import com.example.katalogfilm_byfadli.utils.AppExecutors
import com.example.katalogfilm_byfadli.utils.DataDummy
import com.example.katalogfilm_byfadli.utils.LiveDataTestUtil
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val responseMovie = DataDummy.loadMoviesData()
    private val responseTvShow = DataDummy.loadTvShowsData()


    @Test
    fun getRecommendationsMovies(): Unit = runBlocking {
        `when`(remote.getRecommendationMovies()).thenReturn(ApiResponse.success(responseMovie))
        val moviesEntities = LiveDataTestUtil.getValue(movieRepository.getRecommendationsMovies(10))
        verify(remote).getRecommendationMovies()
        assertThat(moviesEntities).isNotNull()
        assertThat(responseMovie.size).isEqualTo(moviesEntities.data?.size)
    }

    @Test
    fun getRecommendationsTvShows() = runBlocking {
        `when`(remote.getRecommendationTvShows()).thenReturn(ApiResponse.success(responseTvShow))
        val tvShowEntities =
            LiveDataTestUtil.getValue(movieRepository.getRecommendationsTvShows(12))
        verify(remote).getRecommendationTvShows()
        assertThat(tvShowEntities).isNotNull()
        assertThat(responseTvShow.size).isEqualTo(tvShowEntities.data?.size)
    }

    @Test
    fun getFavoritesMovies() {
        val pagingSource = mock(PagingSource::class.java) as PagingSource<Int, MovieEntity>
        `when`(local.getFavoritesMovies("")).thenReturn(pagingSource)
        val moviePagingData = movieRepository.getFavoritesMovies("")
        assertThat(moviePagingData).isNotNull()
    }

    @Test
    fun getFavoritesTvShow() {
        val pagingSource = mock(PagingSource::class.java) as PagingSource<Int, MovieEntity>
        `when`(local.getFavoritesTvShow("")).thenReturn(pagingSource)
        val tvShowPagingData = movieRepository.getFavoritesTvShows("")
        assertThat(tvShowPagingData).isNotNull()
    }
}