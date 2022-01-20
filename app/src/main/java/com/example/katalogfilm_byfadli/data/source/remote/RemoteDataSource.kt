package com.example.katalogfilm_byfadli.data.source.remote

import com.example.katalogfilm_byfadli.BuildConfig
import com.example.katalogfilm_byfadli.BuildConfig.BASE_URL
import com.example.katalogfilm_byfadli.data.source.remote.Response.MovieItem
import com.example.katalogfilm_byfadli.data.source.remote.Response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getRecommendationMovies(): List<MovieItem?>? {
        return withContext(Dispatchers.IO) {
            val response: Response<MovieResponse> = apiService.getRecommendedMovie("605ea935b84f94003d1256f9")
            when (response.code()) {
                200 -> response.body()?.results
                else -> null
            }
        }
    }
}