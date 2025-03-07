package com.app.mycinemaapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.mycinemaapp.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}