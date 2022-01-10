package com.example.katalogfilm_byfadli.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.katalogfilm_byfadli.data.MovieEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException
import com.google.common.truth.Truth.assertThat


class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
        viewModel.loadMoviesData()
    }

    @Test
    fun getMoviesData() {
        val moviesData = viewModel.getMoviesData().value
        assertThat(moviesData).isNotEmpty()
        assertThat(moviesData).isNotNull()
        assertThat(moviesData?.size).isEqualTo(10)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun moviesDataEmpty() {
        viewModel.loadSearchedMoviesData("asfjdasokflanlfi")
        val moviesData = viewModel.getMoviesData().value
        assertThat(moviesData).isNotNull()
        assertThat(moviesData?.size).isEqualTo(0)
        moviesData!![0].title
    }

    @Test(expected = NullPointerException::class)
    fun movieDataFailedToGet() {
        val moviesData: List<MovieEntity>? = null
        assertThat(moviesData).isNull()
        moviesData!![0].title
    }
}