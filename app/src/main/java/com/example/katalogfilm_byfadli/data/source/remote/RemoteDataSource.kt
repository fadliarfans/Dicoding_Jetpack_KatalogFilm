package com.example.katalogfilm_byfadli.data.source.remote

import com.example.katalogfilm_byfadli.data.source.remote.response.MovieItem
import com.example.katalogfilm_byfadli.data.source.remote.response.MovieResponse
import com.example.katalogfilm_byfadli.data.source.remote.response.TvShowItem
import com.example.katalogfilm_byfadli.data.source.remote.response.TvShowResponse
import com.example.katalogfilm_byfadli.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getRecommendationMovies(): ApiResponse<List<MovieItem?>?> {
        return withContext(Dispatchers.IO) {
            EspressoIdlingResource.increment()
            val response: Response<MovieResponse> =
                apiService.getMovie(type = "recommendations", page = "1")
            try {
                when {
                    response.code() == 200 -> {
                        EspressoIdlingResource.decrement()
                        ApiResponse(
                            StatusResponse.SUCCESS,
                            response.body()?.results,
                            null
                        )
                    }
                    response.body()?.results?.isEmpty() == true -> {
                        EspressoIdlingResource.decrement()
                        ApiResponse(StatusResponse.EMPTY, null, "Data Empty")
                    }
                    else -> {
                        EspressoIdlingResource.decrement()
                        ApiResponse(StatusResponse.ERROR, null, "Error Happened")
                    }
                }
            } catch (e: Throwable) {
                EspressoIdlingResource.decrement()
                ApiResponse(StatusResponse.ERROR, null, e.localizedMessage)
            }
        }
    }

    suspend fun getRecommendationTvShows(): ApiResponse<List<TvShowItem?>?> {
        return withContext(Dispatchers.IO) {
            EspressoIdlingResource.increment()
            val response: Response<TvShowResponse> =
                apiService.getTvShow(type = "recommendations", page = "1")
            try {
                when {
                    response.code() == 200 -> {
                        EspressoIdlingResource.decrement()
                        ApiResponse(
                            StatusResponse.SUCCESS,
                            response.body()?.results,
                            null
                        )
                    }
                    response.body()?.results?.isEmpty() == true -> {
                        EspressoIdlingResource.decrement()
                        ApiResponse(StatusResponse.EMPTY, null, "Data Empty")
                    }
                    else -> {
                        EspressoIdlingResource.decrement()
                        ApiResponse(StatusResponse.ERROR, null, "Error Happened")
                    }
                }
            } catch (e: Throwable) {
                EspressoIdlingResource.decrement()
                ApiResponse(StatusResponse.ERROR, null, e.localizedMessage)
            }
        }
    }
}