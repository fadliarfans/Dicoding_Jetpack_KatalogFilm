package com.example.katalogfilm_byfadli.ui.movieFavorite

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.katalogfilm_byfadli.data.source.MovieRepository
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFavoriteViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val searchData = MutableLiveData<String>()

    init {
        searchData.value = ""
    }

    fun loadMovieOrTvShowData(isTv: Boolean): LiveData<PagingData<MovieEntity>> =
        if (isTv) {
            Transformations.switchMap(searchData) { title ->
                movieRepository.getFavoritesTvShows(
                    title
                ).cachedIn(viewModelScope)
            }
        } else {
            Transformations.switchMap(searchData) { title ->
                movieRepository.getFavoritesMovies(title).cachedIn(viewModelScope)
            }
        }

    fun setSearchData(value: String) {
        searchData.value = value
    }
}
