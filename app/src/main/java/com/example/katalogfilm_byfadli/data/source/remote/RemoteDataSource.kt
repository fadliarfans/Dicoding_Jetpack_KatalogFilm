package com.example.katalogfilm_byfadli.data.source.remote

import com.example.katalogfilm_byfadli.data.source.remote.Response.MovieItem
import com.example.katalogfilm_byfadli.data.source.remote.Response.MovieResponse
import com.example.katalogfilm_byfadli.data.source.remote.Response.TvShowItem
import com.example.katalogfilm_byfadli.data.source.remote.Response.TvShowResponse
import com.example.katalogfilm_byfadli.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getRecommendationMovies(): List<MovieItem?>? {
        return withContext(Dispatchers.IO) {
            EspressoIdlingResource.increment()
            val response: Response<MovieResponse> =
                apiService.getMovie(type = "recommendations", page = "1")
            if (response.code() == 200) {
                EspressoIdlingResource.decrement()
                response.body()?.results
            }
            else {
                EspressoIdlingResource.decrement()
                null
            }
        }
    }

    suspend fun getRecommendationTvShows(): List<TvShowItem?>? {
        return withContext(Dispatchers.IO) {
            EspressoIdlingResource.increment()
            val response: Response<TvShowResponse> =
                apiService.getTvShow(type = "recommendations", page = "1")
            if (response.code() == 200) {
                EspressoIdlingResource.decrement()
                response.body()?.results
            }
            else{
                EspressoIdlingResource.decrement()
                null
            }
        }
    }

    suspend fun getFavoritesMovies(): List<MovieItem?>? {
        return withContext(Dispatchers.IO) {
            EspressoIdlingResource.increment()
            val response: Response<MovieResponse> =
                apiService.getMovie(type = "favorites", page = "1")
            if (response.code() == 200) {
                EspressoIdlingResource.decrement()
                response.body()?.results
            }
            else {
                EspressoIdlingResource.decrement()
                null
            }
        }
    }

    suspend fun getFavoritesTvShows(): List<TvShowItem?>? {
        return withContext(Dispatchers.IO) {
            EspressoIdlingResource.increment()
            val response: Response<TvShowResponse> =
                apiService.getTvShow(type = "favorites", page = "1")
            if (response.code() == 200) {
                EspressoIdlingResource.decrement()
                response.body()?.results
            }
            else {
                EspressoIdlingResource.decrement()
                null
            }
        }
    }
}