package com.example.katalogfilm_byfadli.data

data class TvShowEntity(
    val title: String,
    val description: String,
    val yearBegin: Int,
    val yearEnd: Int,
    val imagePath: String,
    val rating: Double,
    val genres: List<String>,
    val totalEpisodes:Int,
    val durationHour: Int,
    val durationMinute: Int,
    val creator: String,
    val stars: List<String>
)