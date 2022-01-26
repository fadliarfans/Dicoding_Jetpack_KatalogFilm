package com.example.katalogfilm_byfadli.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val overview: String?,
    val title: String?,
    val genreIds: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val date: String?,
    val voteAverage: Double?,
    val id: Int?,
    val voteCount: Int?,val isTv:Boolean?):Parcelable

