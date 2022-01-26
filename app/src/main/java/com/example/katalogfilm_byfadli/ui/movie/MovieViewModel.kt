package com.example.katalogfilm_byfadli.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.data.Result
import com.example.katalogfilm_byfadli.data.source.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val moviesData: MutableLiveData<Result<List<MovieEntity>>> = MutableLiveData()
    private var moviesList: List<MovieEntity> = Collections.emptyList()

    fun loadMoviesData() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesData.postValue(Result.Loading)
            val result = movieRepository.getFavoritesMovies(10)
            moviesData.postValue(result)
            moviesList = if (result is Result.Success) result.data else listOf()
        }
    }

    fun loadSearchedMoviesData(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if(moviesData.value is Result.Success){
                moviesData.postValue(Result.Loading)
                val searchedData =
                    moviesList.filter {
                        (it.title?.lowercase()?.contains(query.lowercase()) ?: false)
                    }
                        .toMutableList()
                moviesData.postValue(Result.Success(searchedData))
            }
        }
    }

    fun getMoviesData(): LiveData<Result<List<MovieEntity>>> = moviesData
}