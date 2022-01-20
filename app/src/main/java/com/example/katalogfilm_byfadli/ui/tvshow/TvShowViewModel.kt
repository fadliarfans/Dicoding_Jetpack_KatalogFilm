package com.example.katalogfilm_byfadli.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.utils.DataDummy

class TvShowViewModel : ViewModel() {
    private val tvShowsData: MutableLiveData<List<MovieEntity>> = MutableLiveData()

    fun loadTvShowsData() {
        tvShowsData.value = DataDummy.loadTvShowsData()
    }

    fun loadSearchedTvShowsData(query: String) {
        val data: List<MovieEntity> = DataDummy.loadTvShowsData()
        val resultData = mutableListOf<MovieEntity>()
        data.forEach {
            if (it.title?.lowercase()?.contains(query.lowercase()) == true) {
                resultData.add(it)
            }
        }
        tvShowsData.value = resultData
    }

    fun getTvShowsData(): LiveData<List<MovieEntity>> {
        return tvShowsData
    }
}