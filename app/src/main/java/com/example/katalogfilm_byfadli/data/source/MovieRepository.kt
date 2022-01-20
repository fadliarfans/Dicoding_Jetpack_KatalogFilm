package com.example.katalogfilm_byfadli.data.source

import com.example.katalogfilm_byfadli.data.source.remote.RemoteDataSource
import com.example.katalogfilm_byfadli.data.source.remote.Response.MovieItem
import javax.inject.Inject

class MovieRepository @Inject constructor(private val remoteDataSource: RemoteDataSource):MovieDataSource {
    override suspend fun getRecommendationMovies(): List<MovieItem?>? = remoteDataSource.getRecommendationMovies()
}