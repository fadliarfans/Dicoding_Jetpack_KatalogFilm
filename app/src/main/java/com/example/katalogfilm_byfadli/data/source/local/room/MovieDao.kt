package com.example.katalogfilm_byfadli.data.source.local.room

import androidx.room.*
import com.example.katalogfilm_byfadli.data.source.local.entity.MovieEntity
import androidx.paging.DataSource
import androidx.paging.PagingSource

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieEntities WHERE isTv = 0")
    fun getFavoritesMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE isTv = 1")
    fun getFavoritesTvShow(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE id =:id")
    fun isDataExist(id:Int):List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorites(Movies: List<MovieEntity>)

    @Update
    fun updateFavorites(Movie: MovieEntity)

    @Delete
    fun deleteFavorites(Movie: MovieEntity)




//    @Query("UPDATE moduleentities SET content = :content WHERE moduleId = :moduleId")
//    fun updateModuleByContent(content: String, moduleId: String)
}