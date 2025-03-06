package com.app.mycinemaapp.di

import com.app.mycinemaapp.core.domain.usecase.MovieInteractor
import com.app.mycinemaapp.core.domain.usecase.MovieUseCase
import com.app.mycinemaapp.detail.DetailMovieViewModel
import com.app.mycinemaapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}