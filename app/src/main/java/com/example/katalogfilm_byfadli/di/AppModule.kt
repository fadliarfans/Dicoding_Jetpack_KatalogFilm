package com.example.katalogfilm_byfadli.di

import android.content.Context
import androidx.room.Room
import com.example.katalogfilm_byfadli.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        "movies.db"
    ).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDatabase) = db.movieDao()
}