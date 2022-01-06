package com.example.katalogfilm_byfadli.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.utils.DataDummy
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val moviesData: MutableLiveData<List<MovieEntity>> = MutableLiveData()

    fun loadMoviesData() {
        moviesData.value = DataDummy.loadMoviesData()
    }

    fun loadSearchedMoviesData(query: String) {
        viewModelScope.launch {
            val data: List<MovieEntity> = DataDummy.loadMoviesData()
            val resultData = mutableListOf<MovieEntity>()
            data.forEach {
                if (it.title?.lowercase()?.contains(query.lowercase()) == true) {
                    resultData.add(it)
                }
            }
            moviesData.value = resultData
        }
    }

    fun getMoviesData(): LiveData<List<MovieEntity>> {
        return moviesData
    }
}