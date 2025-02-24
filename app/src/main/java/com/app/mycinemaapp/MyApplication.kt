package com.app.mycinemaapp

import android.app.Application
import com.app.mycinemaapp.core.di.databaseModule
import com.app.mycinemaapp.core.di.networkModule
import com.app.mycinemaapp.core.di.repositoryModule
import com.app.mycinemaapp.di.useCaseModule
import com.app.mycinemaapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}