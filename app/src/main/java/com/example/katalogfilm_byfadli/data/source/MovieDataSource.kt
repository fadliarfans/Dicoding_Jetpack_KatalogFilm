package com.example.katalogfilm_byfadli.data.source

import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.data.Result

interface MovieDataSource {
    suspend fun getFavoritesMovies(total:Int): Result<List<MovieEntity>>
    suspend fun getFavoritesTvShows(total:Int): Result<List<MovieEntity>>
}