package com.example.katalogfilm_byfadli.data.source

import com.example.katalogfilm_byfadli.data.source.remote.Response.MovieItem

interface MovieDataSource {
    suspend fun getRecommendationMovies(): List<MovieItem?>?
}