package com.example.katalogfilm_byfadli.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movieEntities")
data class MovieEntity(
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "genreIds")
    val genreIds: String?,
    @ColumnInfo(name = "posterPath")
    val posterPath: String?,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String?,
    @ColumnInfo(name = "date")
    val date: String?,
    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double?,
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "voteCount")
    val voteCount: Int?, val isTv: Boolean?
) : Parcelable

