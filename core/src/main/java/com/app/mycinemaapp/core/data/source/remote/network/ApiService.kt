package com.app.mycinemaapp.core.data.source.remote.network

import com.app.mycinemaapp.core.data.source.remote.response.DetailMovieResponse
import com.app.mycinemaapp.core.data.source.remote.response.NowPlayingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NowPlayingMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int
    ): DetailMovieResponse
}