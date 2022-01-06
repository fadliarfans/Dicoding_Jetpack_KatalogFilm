package com.example.katalogfilm_byfadli.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogfilm_byfadli.data.TvShowEntity
import com.example.katalogfilm_byfadli.utils.DataDummy
import kotlinx.coroutines.launch

class TvShowViewModel : ViewModel() {
    private val tvShowsData: MutableLiveData<List<TvShowEntity>> = MutableLiveData()

    fun loadTvShowsData() {
        tvShowsData.value = DataDummy.loadTvShowsData()
    }

    fun loadSearchedTvShowsData(query: String) {
        viewModelScope.launch {
            val data: List<TvShowEntity> = DataDummy.loadTvShowsData()
            val resultData = mutableListOf<TvShowEntity>()
            data.forEach {
                if (it.name?.lowercase()?.contains(query.lowercase()) == true) {
                    resultData.add(it)
                }
            }
            tvShowsData.value = resultData
        }
    }

    fun getTvShowsData(): LiveData<List<TvShowEntity>> {
        return tvShowsData
    }
}