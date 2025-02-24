package com.app.mycinemaapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.mycinemaapp.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}