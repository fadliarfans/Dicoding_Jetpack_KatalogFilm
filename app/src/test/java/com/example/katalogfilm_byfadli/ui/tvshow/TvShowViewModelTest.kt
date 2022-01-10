package com.example.katalogfilm_byfadli.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.katalogfilm_byfadli.data.TvShowEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException
import com.google.common.truth.Truth.assertThat

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
        assertThat(tvShowsData).isNotEmpty()
        assertThat(tvShowsData).isNotNull()
        assertThat(tvShowsData?.size).isEqualTo(10)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun tvShowsDataEmpty() {
        viewModel.loadSearchedTvShowsData("asfjdasokflanlfi")
        val tvShowsData = viewModel.getTvShowsData().value
        assertThat(tvShowsData).isNotNull()
        assertThat(tvShowsData?.size).isEqualTo(0)
        tvShowsData!![0].name
    }

    @Test(expected = NullPointerException::class)
    fun tvShowDataFailedToGet() {
        val tvShowsData: List<TvShowEntity>? = null
        assertThat(tvShowsData).isNull()
        tvShowsData!![0].name
    }
}