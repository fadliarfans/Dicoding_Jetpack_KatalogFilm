package com.example.katalogfilm_byfadli.data.source.remote

import com.example.katalogfilm_byfadli.BuildConfig.ACCOUNT_ID
import com.example.katalogfilm_byfadli.data.source.remote.response.MovieResponse
import com.example.katalogfilm_byfadli.data.source.remote.response.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("account/$ACCOUNT_ID/movie/{type}")
    suspend fun getMovie(
        @Path("type") type: String,
        @Query("page") page: String

    ): Response<MovieResponse>

    @GET("account/$ACCOUNT_ID/tv/{type}")
    suspend fun getTvShow(
        @Path("type") type: String,
        @Query("page") page: String

    ): Response<TvShowResponse>
}