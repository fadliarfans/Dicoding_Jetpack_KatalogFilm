package com.example.katalogfilm_byfadli.ui.detail_tvshow

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import java.lang.NullPointerException

class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel()
    }

    @Test
    fun getDetailTvShow() {
        val detailTvShow = viewModel.getDetailTvShow(77169)
        assertNotNull(detailTvShow)
    }

    @Test(expected = NullPointerException::class)
    fun detailTvShowFailedToGet() {
        val tvShow = viewModel.getDetailTvShow(123456)
        println(tvShow!!.name)
    }
}