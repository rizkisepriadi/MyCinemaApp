package com.app.mycinemaapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String?,
    val releaseDate: String,
    val rating: Double,
    val genres: List<Genre>,
    val runtime: Int,
    val language: String,
    val tagline: String,
    val status: String,
    val homepage: String,
    val isFavorite: Boolean
) : Parcelable

@Parcelize
data class Genre(
    val id: Int,
    val name: String
) : Parcelable
