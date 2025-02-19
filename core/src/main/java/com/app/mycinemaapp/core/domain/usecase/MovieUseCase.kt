package com.app.mycinemaapp.core.domain.usecase

import com.app.mycinemaapp.core.data.source.Resource
import com.app.mycinemaapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}