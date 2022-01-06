package com.example.katalogfilm_byfadli.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.katalogfilm_byfadli.data.MovieEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestRule
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMoviesdata() {
        viewModel.loadMoviesData()
        val moviesData = viewModel.getMoviesData().value
        assertNotNull(moviesData)
        assertEquals(10, moviesData?.size)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun moviesDataEmpty() {
        val moviesData = mutableListOf<MovieEntity>()
        val movie = moviesData[0]
        println(movie.title)
    }

    @Test(expected = NullPointerException::class)
    fun moviesDataFailedToGet() {
        val moviesData: List<MovieEntity>? = null
        val firstMovie = moviesData!![0]
        println(firstMovie.title)
    }
}