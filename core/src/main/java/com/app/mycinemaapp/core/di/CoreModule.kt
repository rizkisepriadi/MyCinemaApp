package com.app.mycinemaapp.core.di

import androidx.room.Room
import com.app.mycinemaapp.core.data.source.CinemaRepository
import com.app.mycinemaapp.core.data.source.local.LocalDataSource
import com.app.mycinemaapp.core.data.source.local.room.MovieDatabase
import com.app.mycinemaapp.core.data.source.remote.RemoteDataSource
import com.app.mycinemaapp.core.data.source.remote.network.ApiService
import com.app.mycinemaapp.core.domain.repository.IMovieRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "movie.db"
        ).fallbackToDestructiveMigrationFrom().build()
    }
}

val networkModule = module {
    single {
        val authInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlYTFlZWI1YWMxYzNiNWM2NTAyY2EyMjhhYTM1Njc1NiIsIm5iZiI6MTczOTg5MjMzMi45MzEsInN1YiI6IjY3YjRhNjZjZTBkOWY4MzNiYzZkYzhlMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.N_d3GxxzraNsNV02F-0e8ZQR4ZTEoUGawbTqgtIIhF8"
                )
                .build()
            chain.proceed(request)
        }

        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }

    val repositoryModule = module {
        single { RemoteDataSource(get()) }
        single { LocalDataSource(get()) }
        single<IMovieRepository> {
            CinemaRepository(
                get(),
                get(),
            )
        }
    }
}