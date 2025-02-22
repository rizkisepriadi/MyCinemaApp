package com.app.mycinemaapp.core.data.source

import com.app.mycinemaapp.core.data.source.local.LocalDataSource
import com.app.mycinemaapp.core.data.source.remote.RemoteDataSource
import com.app.mycinemaapp.core.data.source.remote.network.ApiResponse
import com.app.mycinemaapp.core.data.source.remote.response.NowPlayingMoviesResponse
import com.app.mycinemaapp.core.domain.model.Movie
import com.app.mycinemaapp.core.domain.repository.IMovieRepository
import com.app.mycinemaapp.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CinemaRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, NowPlayingMoviesResponse>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<NowPlayingMoviesResponse>> =
                remoteDataSource.getNowPlayingMovies()

            override suspend fun saveCallResult(data: NowPlayingMoviesResponse) {
                val movieList = DataMapper.mapNowPlayingToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()


    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getAllFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        withContext(Dispatchers.IO) {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }
}