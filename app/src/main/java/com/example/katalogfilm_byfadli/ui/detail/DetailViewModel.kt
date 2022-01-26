package com.example.katalogfilm_byfadli.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.data.Result

class DetailViewModel :
    ViewModel() {
    private val detailMovieOrTvShowData:MutableLiveData<Result<MovieEntity>> = MutableLiveData()

    fun setDetailMovieOrTvShowData(movieEntity: MovieEntity?){
        try {
            detailMovieOrTvShowData.value = Result.Success(movieEntity!!)
        }catch (e:Throwable){
            detailMovieOrTvShowData.value = Result.Error(e)
        }
    }

    fun getDetailMovieOrTvShowData():LiveData<Result<MovieEntity>> = detailMovieOrTvShowData
}