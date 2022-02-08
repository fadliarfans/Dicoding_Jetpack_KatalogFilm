package com.example.katalogfilm_byfadli.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import com.example.katalogfilm_byfadli.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    fun getFavoritesMovies(): LiveData<PagingData<MovieEntity>>
    fun getFavoritesTvShows(): LiveData<PagingData<MovieEntity>>
    suspend fun getRecommendationsMovies(total:Int): LiveData<Resource<List<MovieEntity>>>
    suspend fun getRecommendationsTvShows(total:Int): LiveData<Resource<List<MovieEntity>>>
    suspend fun insertFavorites(movieEntity: MovieEntity?)
    suspend fun deleteFavorites(movieEntity: MovieEntity?)
    suspend fun isDataExist(id:Int):List<MovieEntity>
}