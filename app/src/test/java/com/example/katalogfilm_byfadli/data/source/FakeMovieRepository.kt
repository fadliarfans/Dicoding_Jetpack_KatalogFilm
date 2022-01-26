package com.example.katalogfilm_byfadli.data.source

import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.data.Result
import com.example.katalogfilm_byfadli.data.source.remote.RemoteDataSource
import com.example.katalogfilm_byfadli.utils.generateMovieEntities

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

    override suspend fun getFavoritesTvShows(total: Int): Result<List<MovieEntity>> {
        return try {
            val responseList = remoteDataSource.getFavoritesTvShows()
            val moviesEntities = responseList.generateMovieEntities(12)
            Result.Success(moviesEntities)
        }catch (e:Throwable){
            Result.Error(e)
        }
    }
}