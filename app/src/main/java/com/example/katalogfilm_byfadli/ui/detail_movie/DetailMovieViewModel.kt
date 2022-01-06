package com.example.katalogfilm_byfadli.ui.detail_movie

import androidx.lifecycle.ViewModel
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.utils.DataDummy
import com.example.katalogfilm_byfadli.utils.GlobalFunctions

class DetailMovieViewModel : ViewModel() {
    fun getDetailMovie(movieId: Int): MovieEntity? {
        val listMovies = DataDummy.loadMoviesData()
        var movie: MovieEntity? = null
        for (it in listMovies) {
            if (movieId == it.id) {
                movie = it
                break
            }
        }
        return movie
    }

    fun generateGenre(listOfIdGenres: List<Int?>?): String =
        GlobalFunctions.generateGenre(listOfIdGenres)
}