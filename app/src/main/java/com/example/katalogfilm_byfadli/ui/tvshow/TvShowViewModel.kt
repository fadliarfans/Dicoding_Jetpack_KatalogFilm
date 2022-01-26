package com.example.katalogfilm_byfadli.ui.tvshow

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
class TvShowViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val tvShowsData: MutableLiveData<Result<List<MovieEntity>>> = MutableLiveData()
    private var tvShowsList: List<MovieEntity> = Collections.emptyList()

    fun loadTvShowData() {
        viewModelScope.launch(Dispatchers.IO) {
            tvShowsData.postValue(Result.Loading)
            val result = movieRepository.getFavoritesTvShows(12)
            tvShowsData.postValue(result)
            tvShowsList = if (result is Result.Success) result.data else listOf()
        }
    }

    fun loadSearchedTvShowData(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if(tvShowsData.value is Result.Success){
                tvShowsData.postValue(Result.Loading)
                val searchedData =
                    tvShowsList.filter { (it.title?.lowercase()?.contains(query.lowercase()) ?: false) }
                        .toMutableList()
                tvShowsData.postValue(Result.Success(searchedData))
            }
        }
    }

    fun getTvShowsData(): LiveData<Result<List<MovieEntity>>> = tvShowsData
}