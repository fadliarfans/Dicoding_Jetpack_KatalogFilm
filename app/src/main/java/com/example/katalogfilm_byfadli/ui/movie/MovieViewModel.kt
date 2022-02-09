package com.example.katalogfilm_byfadli.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import com.example.katalogfilm_byfadli.data.source.MovieRepository
import com.example.katalogfilm_byfadli.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import com.example.katalogfilm_byfadli.vo.Status.*

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val movieOrTvShowData: MutableLiveData<Resource<List<MovieEntity>>> = MutableLiveData()
    private var moviesOrTvShowList: List<MovieEntity> = Collections.emptyList()

    fun loadMovieOrTvShowDataData(isTv: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            movieOrTvShowData.postValue(Resource.loading(null))
            moviesOrTvShowList = if (isTv) {
                val result = movieRepository.getRecommendationsTvShows(10)
                movieOrTvShowData.postValue(result.value)
                if (result.value?.status == SUCCESS) result.value?.data ?: listOf() else listOf()
            } else {
                val result = movieRepository.getRecommendationsMovies(10)
                movieOrTvShowData.postValue(result.value)
                if (result.value?.status == SUCCESS) result.value?.data ?: listOf() else listOf()
            }
        }
    }

    fun loadSearchedData(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (movieOrTvShowData.value?.status == SUCCESS) {
                movieOrTvShowData.postValue(Resource.loading(null))
                val searchedData =
                    moviesOrTvShowList.filter {
                        (it.title?.lowercase()?.contains(query.lowercase()) ?: false)
                    }
                        .toMutableList()
                movieOrTvShowData.postValue(Resource.success(searchedData))
            }
        }
    }

    fun getData(): LiveData<Resource<List<MovieEntity>>> = movieOrTvShowData
}