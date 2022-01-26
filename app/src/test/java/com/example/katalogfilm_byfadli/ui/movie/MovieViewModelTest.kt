package com.example.katalogfilm_byfadli.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.data.Result
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
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Result<List<MovieEntity>>>


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMoviesData() = runBlocking {
        val dummyMovies = DataDummy.loadMoviesData().generateMovieEntities(10)

        `when`(movieRepository.getFavoritesMovies(10)).thenReturn(Result.Success(dummyMovies))
        viewModel.loadMoviesData()
        verify(movieRepository).getFavoritesMovies(10)

        val moviesEntities = viewModel.getMoviesData()
        assertThat(moviesEntities).isNotNull()
        assertThat((moviesEntities.value as Result.Success).data.size).isEqualTo(10)

        viewModel.getMoviesData().observeForever(observer)
        verify(observer).onChanged(Result.Success(dummyMovies))
    }
}