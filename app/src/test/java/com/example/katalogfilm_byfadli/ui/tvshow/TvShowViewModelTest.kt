package com.example.katalogfilm_byfadli.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.katalogfilm_byfadli.data.TvShowEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestRule
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShowsData() {
        viewModel.loadTvShowsData()
        val tvShowsData = viewModel.getTvShowsData().value
        assertNotNull(tvShowsData)
        assertEquals(10, tvShowsData?.size)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun tvShowsDataEmpty() {
        val tvShowsData = mutableListOf<TvShowEntity>()
        val tvShow = tvShowsData[0]
        println(tvShow.name)
    }

    @Test(expected = NullPointerException::class)
    fun tvShowDataFailedToGet() {
        val tvShowData: List<TvShowEntity>? = null
        val firstTvShow = tvShowData!![0]
        println(firstTvShow.name)

    }

}