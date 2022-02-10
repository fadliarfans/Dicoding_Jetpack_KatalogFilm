package com.example.katalogfilm_byfadli.data.source

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
                    val listMovieEntity = this.body.generateMovieEntities(total)
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
                    val listMovieEntity = this.body.generateMovieEntities(total)
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

    override fun insertFavorites(movieEntity: MovieEntity?) {
        appExecutors.diskIO().execute {
            if (movieEntity != null) localDataSource.insertFavorite(mutableListOf(movieEntity))
        }

    }

    override fun deleteFavorites(movieEntity: MovieEntity?) {
        appExecutors.diskIO().execute {
            if (movieEntity != null) localDataSource.deleteFavorite(movieEntity)
        }
    }

    override fun getFavoritesMovies(title: String?) =
        Pager(config = PagingConfig(pageSize = 20)) {
            localDataSource.getFavoritesMovies(title ?: "")
        }.liveData

    override fun getFavoritesTvShows(title: String?) =
        Pager(config = PagingConfig(pageSize = 20)) {
            localDataSource.getFavoritesTvShow(title ?: "")
        }.liveData

    override fun isDataExist(id: Int): Boolean {
        return localDataSource.isDataExist(id).isNotEmpty()
    }
}