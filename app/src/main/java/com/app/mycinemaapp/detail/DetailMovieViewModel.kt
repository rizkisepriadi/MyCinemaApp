package com.app.mycinemaapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.app.mycinemaapp.core.domain.model.Movie
import com.app.mycinemaapp.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMovieById(movieId: Int) = movieUseCase.getMovieById(movieId).asLiveData()

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) {
        viewModelScope.launch {
            movieUseCase.setFavoriteMovie(movie, newStatus)
        }
    }
}
