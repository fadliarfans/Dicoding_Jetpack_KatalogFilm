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

    fun loadMovieOrTvShowData(isTv: Boolean):LiveData<PagingData<MovieEntity>> =
        if (isTv) {
            movieRepository.getFavoritesTvShows().cachedIn(viewModelScope)
        } else {
            movieRepository.getFavoritesMovies().cachedIn(viewModelScope)
        }
}
