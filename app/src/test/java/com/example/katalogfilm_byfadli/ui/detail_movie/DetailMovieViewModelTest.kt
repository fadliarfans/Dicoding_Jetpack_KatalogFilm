package com.example.katalogfilm_byfadli.ui.detail_movie

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.NullPointerException


class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
    }

    @Test
    fun getDetailMovie() {
        val detailMovie = viewModel.getDetailMovie(728526)
        assertNotNull(detailMovie)
    }

    @Test(expected = NullPointerException::class)
    fun detailMovieFailedToGet() {
        val movie = viewModel.getDetailMovie(123456)
        println(movie!!.title)
    }
}