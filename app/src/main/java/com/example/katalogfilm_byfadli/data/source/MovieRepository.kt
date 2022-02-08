package com.example.katalogfilm_byfadli.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.example.katalogfilm_byfadli.data.source.local.LocalDataSource
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import com.example.katalogfilm_byfadli.data.source.remote.RemoteDataSource
import com.example.katalogfilm_byfadli.data.source.remote.*
import com.example.katalogfilm_byfadli.utils.AppExecutors
import com.example.katalogfilm_byfadli.utils.generateMovieEntities
import com.example.katalogfilm_byfadli.vo.Resource
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieDataSource {

    override suspend fun getRecommendationsMovies(total: Int): LiveData<Resource<List<MovieEntity>>> {
        val response = remoteDataSource.getRecommendationMovies()
        with(response) {
            return when (this.status) {
                StatusResponse.SUCCESS -> {
                    val listMovieEntity = this.body.generateMovieEntities(10)
                    MutableLiveData(Resource.success(listMovieEntity))
                }
                StatusResponse.ERROR -> {
                    MutableLiveData(Resource.error(this.message, null))
                }
                else -> {
                    MutableLiveData(Resource.success(mutableListOf()))
                }
            }
        }
    }

    override suspend fun getRecommendationsTvShows(total: Int): LiveData<Resource<List<MovieEntity>>> {
        val response = remoteDataSource.getRecommendationTvShows()
        with(response) {
            return when (this.status) {
                StatusResponse.SUCCESS -> {
                    val listMovieEntity = this.body.generateMovieEntities(10)
                    MutableLiveData(Resource.success(listMovieEntity))
                }
                StatusResponse.ERROR -> {
                    MutableLiveData(Resource.error(this.message, null))
                }
                else -> {
                    MutableLiveData(Resource.success(mutableListOf()))
                }
            }
        }
    }

    override suspend fun insertFavorites(movieEntity: MovieEntity?) {
        appExecutors.diskIO().execute {
            if (movieEntity != null) localDataSource.insertFavorite(mutableListOf(movieEntity))
        }

    }

    override suspend fun deleteFavorites(movieEntity: MovieEntity?) {
        appExecutors.diskIO().execute {
            if (movieEntity != null) localDataSource.deleteFavorite(movieEntity)
        }
    }

    override fun getFavoritesMovies() =
        Pager(config = PagingConfig( pageSize = 20)) {
            localDataSource.getFavoritesMovies()
        }.liveData

    override fun getFavoritesTvShows() =
        Pager(config = PagingConfig( pageSize = 20)) {
            localDataSource.getFavoritesTvShow()
        }.liveData

    override suspend fun isDataExist(id: Int): List<MovieEntity> =
        localDataSource.isDataExist(id)
}