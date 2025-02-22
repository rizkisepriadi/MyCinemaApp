package com.app.mycinemaapp.core.utils

import com.app.mycinemaapp.core.data.source.local.entity.MovieEntity
import com.app.mycinemaapp.core.data.source.remote.response.DetailMovieResponse
import com.app.mycinemaapp.core.data.source.remote.response.GenresItem
import com.app.mycinemaapp.core.data.source.remote.response.NowPlayingMoviesResponse
import com.app.mycinemaapp.core.domain.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataMapper {
    fun mapResponsesToEntities(input: List<DetailMovieResponse>): List<MovieEntity> {
        val gson = Gson()
        return input.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterUrl = it.posterPath,
                backdropUrl = it.backdropPath.toString(),
                releaseDate = it.releaseDate,
                rating = it.voteAverage,
                genres = gson.toJson(it.genres),
                runtime = it.runtime,
                language = it.originalLanguage,
                tagline = it.tagline,
                status = it.status,
                homepage = it.homepage,
                isFavorite = false,
            )
        }
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        val gson = Gson()
        val listType = object : TypeToken<List<GenresItem>>() {}.type

        return input.map {
            Movie(
                id = it.id,
                title = it.title,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterUrl = it.posterUrl,
                backdropUrl = it.backdropUrl,
                releaseDate = it.releaseDate,
                rating = it.rating,
                genres = gson.fromJson(it.genres, listType),
                runtime = it.runtime,
                language = it.language,
                tagline = it.tagline,
                status = it.status,
                homepage = it.homepage,
                isFavorite = it.isFavorite,
            )
        }
    }

    fun mapNowPlayingToEntities(input: NowPlayingMoviesResponse): List<MovieEntity> {
        return input.results.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterUrl = it.posterPath,
                backdropUrl = it.backdropPath ?: "",
                releaseDate = it.releaseDate,
                rating = it.voteAverage,
                genres = "[]",
                runtime = 0,
                language = it.originalLanguage,
                tagline = "",
                status = "",
                homepage = "",
                isFavorite = false,
            )
        }
    }


    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        originalTitle = input.originalTitle,
        overview = input.overview,
        posterUrl = input.posterUrl,
        backdropUrl = input.backdropUrl,
        releaseDate = input.releaseDate,
        rating = input.rating,
        genres = Gson().toJson(input.genres),
        runtime = input.runtime,
        language = input.language,
        tagline = input.tagline,
        status = input.status,
        homepage = input.homepage,
        isFavorite = input.isFavorite
    )

}