package com.example.katalogfilm_byfadli.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.data.source.MovieRepository
import com.example.katalogfilm_byfadli.utils.DataDummy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    private val moviesData: MutableLiveData<List<MovieEntity>> = MutableLiveData()

    fun loadMoviesData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = movieRepository.getRecommendationMovies()
                Log.i("DEBUGR",response.toString())
            }catch (e:Throwable){
                Log.i("DEBUGR",e.toString())
            }
        }
    }

    fun loadSearchedMoviesData(query: String) {
        val data: List<MovieEntity> = DataDummy.loadMoviesData()
        val resultData = mutableListOf<MovieEntity>()
        data.forEach {
            if (it.title?.lowercase()?.contains(query.lowercase()) == true) {
                resultData.add(it)
            }
        }
        moviesData.value = resultData
    }

    fun getMoviesData(): LiveData<List<MovieEntity>> {
        return moviesData
    }
}