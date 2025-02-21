package com.app.mycinemaapp.core.data.source.local.room

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.mycinemaapp.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}