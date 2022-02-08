package com.example.katalogfilm_byfadli.ui.detail

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.map
import com.example.katalogfilm_byfadli.data.source.MovieRepository
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val detailMovieOrTvShowData:MutableLiveData<MovieEntity> = MutableLiveData()
    private val favoriteState:MutableLiveData<Boolean> = MutableLiveData()

    init {
        checkFavoriteState()
    }

    private fun checkFavoriteState() {
        viewModelScope.launch(Dispatchers.IO) {
            if(movieRepository.isDataExist(detailMovieOrTvShowData.value?.id?:0).isEmpty()){
                favoriteState.postValue(false)
                Log.v("Debug","Not Favorite")
            }else{
                favoriteState.postValue(true)
                Log.v("Debug","Favorite")
            }
        }
    }

    fun setDetailMovieOrTvShowData(movieEntity: MovieEntity?){
        detailMovieOrTvShowData.value = movieEntity!!
    }

    fun insertFavorite(){
        viewModelScope.launch {
            movieRepository.insertFavorites(detailMovieOrTvShowData.value)
        }
        favoriteState.value = true
    }

    fun deleteFavorite(){
        viewModelScope.launch {
            movieRepository.deleteFavorites(detailMovieOrTvShowData.value)
        }
        favoriteState.value = false
    }

    fun getDetailMovieOrTvShowData():LiveData<MovieEntity> = detailMovieOrTvShowData
    fun getFavoriteState():LiveData<Boolean> = favoriteState
}