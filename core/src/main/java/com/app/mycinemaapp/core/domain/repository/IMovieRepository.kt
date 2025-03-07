package com.app.mycinemaapp.core.domain.repository

import com.app.mycinemaapp.core.data.source.Resource
import com.app.mycinemaapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    suspend fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun getMovieById(movieId: Int): Flow<Movie>
}