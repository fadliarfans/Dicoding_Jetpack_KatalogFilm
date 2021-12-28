package com.example.katalogfilm_byfadli.utils

object GlobalFunctions {
    fun generateGenre(listOfIdGenres: List<Int>): List<String> {
        val listOfGenres = mutableListOf<String>()
        listOfIdGenres.forEach {
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
        return listOfGenres
    }
}