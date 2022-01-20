package com.example.katalogfilm_byfadli.data.source.remote

import com.example.katalogfilm_byfadli.data.source.remote.Response.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("account/{account_id}/movie/recommendations?page=1")
    suspend fun getRecommendedMovie(
        @Path("account_id") account_id: String
    ): Response<MovieResponse>
}