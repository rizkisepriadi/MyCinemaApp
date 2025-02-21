package com.app.mycinemaapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.mycinemaapp.core.data.source.local.entity.MovieEntity
import com.app.mycinemaapp.core.utils.Converters

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}