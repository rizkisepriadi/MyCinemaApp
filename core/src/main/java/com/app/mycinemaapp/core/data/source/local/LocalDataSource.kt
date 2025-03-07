package com.app.mycinemaapp.core.data.source.local

import com.app.mycinemaapp.core.data.source.local.entity.MovieEntity
import com.app.mycinemaapp.core.data.source.local.room.MovieDao
import com.app.mycinemaapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getAllFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovie(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

    fun getMovieById(movieId: Int): Flow<MovieEntity> = movieDao.getMovieById(movieId)
}