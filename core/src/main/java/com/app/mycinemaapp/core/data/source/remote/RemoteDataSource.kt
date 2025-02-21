package com.app.mycinemaapp.core.data.source.remote

import android.util.Log
import com.app.mycinemaapp.core.data.source.remote.network.ApiResponse
import com.app.mycinemaapp.core.data.source.remote.network.ApiService
import com.app.mycinemaapp.core.data.source.remote.response.NowPlayingMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    fun getNowPlayingMovies(): Flow<ApiResponse<NowPlayingMoviesResponse>> {
        return flow {
            val response = apiService.getNowPlayingMovies()
            if (response.totalResults != 0) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        }
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(ApiResponse.Error(e.localizedMessage ?: "Unknown Error"))
                Log.e("RemoteDataSource", "Error fetching movies", e)
            }
    }
}
