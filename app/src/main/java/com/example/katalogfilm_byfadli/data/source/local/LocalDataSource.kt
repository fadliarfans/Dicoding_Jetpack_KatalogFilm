package com.example.katalogfilm_byfadli.data.source.local

import androidx.paging.PagingSource
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import com.example.katalogfilm_byfadli.data.source.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getFavoritesMovies(title: String): PagingSource<Int, MovieEntity> =
        movieDao.getFavoritesMovies(title)

    fun getFavoritesTvShow(title: String): PagingSource<Int, MovieEntity> =
        movieDao.getFavoritesTvShow(title)

    fun isDataExist(id: Int): List<MovieEntity> = movieDao.isDataExist(id)

    fun insertFavorite(listMovieEntity: List<MovieEntity>) =
        movieDao.insertFavorites(listMovieEntity)

    fun deleteFavorite(movieEntity: MovieEntity) = movieDao.deleteFavorites(movieEntity)

}