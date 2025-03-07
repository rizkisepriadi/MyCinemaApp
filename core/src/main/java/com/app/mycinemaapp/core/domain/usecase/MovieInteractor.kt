package com.app.mycinemaapp.core.domain.usecase

import com.app.mycinemaapp.core.domain.model.Movie
import com.app.mycinemaapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()
    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()
    override suspend fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
    override fun getMovieById(movieId: Int): Flow<Movie> = movieRepository.getMovieById(movieId)
}