package com.example.katalogfilm_byfadli.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import com.example.katalogfilm_byfadli.data.source.remote.RemoteDataSource

class FakeMovieRepository (private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {

    override suspend fun getFavoritesMovies(total: Int): Result<List<MovieEntity>> {
        return try {
            val responseList = remoteDataSource.getFavoritesMovies()
            val moviesEntities = responseList.generateMovieEntities(10)
            Result.Success(moviesEntities)
        }catch (e:Throwable){
            Result.Error(e)
        }
    }

    override suspend fun getFavoritesTvShows(total: Int): LiveData<PagedList<List<MovieEntity>>> {
        return try {
            val responseList = remoteDataSource.getFavoritesTvShows()
            val moviesEntities = responseList.generateMovieEntities(12)
            Result.Success(moviesEntities)
        }catch (e:Throwable){
            Result.Error(e)
        }
    }
}