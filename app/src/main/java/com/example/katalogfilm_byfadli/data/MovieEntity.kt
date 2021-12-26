package com.example.katalogfilm_byfadli.data

data class MovieEntity(
    val title: String,
    val description: String,
    val year: Int,
    val imagePath: String,
    val rating: Double,
    val genres: List<String>,
    val durationHour: Int,
    val durationMinute: Int,
    val director:String,
    val writers:List<String>,
    val stars:List<String>
)