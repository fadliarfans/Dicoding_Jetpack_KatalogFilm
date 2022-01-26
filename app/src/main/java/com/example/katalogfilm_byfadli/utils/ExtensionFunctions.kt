package com.example.katalogfilm_byfadli.utils

import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.data.source.remote.Response.MovieItem
import com.example.katalogfilm_byfadli.data.source.remote.Response.TvShowItem

fun List<Int?>?.generateGenre(): String {
    val listOfGenres = mutableListOf<String>()
    this?.forEach {
        var genre = ""
        when (it) {
            28 -> genre = "Action"
            12 -> genre = "Adventure"
            16 -> genre = "Animation"
            35 -> genre = "Comedy"
            80 -> genre = "Crime"
            99 -> genre = "Documentary"
            18 -> genre = "Drama"
            10751 -> genre = "Family"
            14 -> genre = "Fantasy"
            36 -> genre = "History"
            27 -> genre = "Horror"
            10402 -> genre = "Music"
            9648 -> genre = "Mystery"
            10749 -> genre = "Romance"
            878 -> genre = "Science Fiction"
            10770 -> genre = "TV Movie"
            53 -> genre = "Thriller"
            10752 -> genre = "War"
            37 -> genre = "Western"
            10759 -> genre = "Action & Adventure"
            10762 -> genre = "Kids"
            10763 -> genre = "News"
            10764 -> genre = "Reality"
            10765 -> genre = "SciFi & Fantasy"
            10766 -> genre = "Soap"
            10767 -> genre = "Talk"
            10768 -> genre = "War & Politics"
        }
        listOfGenres.add(genre)
    }
    var stringListOfGenres = listOfGenres.toString()
    stringListOfGenres =
        stringListOfGenres.subSequence(1, stringListOfGenres.length - 1).toString()
    return stringListOfGenres
}

fun String?.changeDateFormat(): String {
    val year = this?.subSequence(0, 4)
    val month = this?.subSequence(5, 7)
    val day = this?.subSequence(8, 10)
    var monthS = "default"

    when (month) {
        "01" -> monthS = "January"
        "02" -> monthS = "February"
        "03" -> monthS = "March"
        "04" -> monthS = "April"
        "05" -> monthS = "Mei"
        "06" -> monthS = "June"
        "07" -> monthS = "July"
        "08" -> monthS = "August"
        "09" -> monthS = "September"
        "10" -> monthS = "October"
        "11" -> monthS = "November"
        "12" -> monthS = "Desember"
    }
    return "$day $monthS $year"
}

fun <T> List<T?>?.generateMovieEntities(total: Int): List<MovieEntity> {
    val movieEntities = mutableListOf<MovieEntity>()
    for (movie in this ?: mutableListOf()) {
        if (movieEntities.size == total) {
            break
        }
        if (movie is MovieItem?) {
            movieEntities.add(
                MovieEntity(
                    title = movie?.title,
                    overview = movie?.overview,
                    genreIds = movie?.genreIds.generateGenre(),
                    posterPath = movie?.posterPath,
                    backdropPath = movie?.backdropPath,
                    date = movie?.releaseDate.changeDateFormat(),
                    id = movie?.id, voteAverage = movie?.voteAverage,
                    voteCount = movie?.voteCount,
                    isTv = false,
                )
            )
        } else if (movie is TvShowItem?) {
            movieEntities.add(
                MovieEntity(
                    title = movie?.name,
                    overview = movie?.overview,
                    genreIds = movie?.genreIds.generateGenre(),
                    posterPath = movie?.posterPath,
                    backdropPath = movie?.backdropPath,
                    date = movie?.firstAirDate.changeDateFormat(),
                    id = movie?.id, voteAverage = movie?.voteAverage,
                    voteCount = movie?.voteCount,
                    isTv = true
                )
            )
        }
    }
    return movieEntities
}


