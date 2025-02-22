package com.app.mycinemaapp.core.utils

import androidx.room.TypeConverters
import com.app.mycinemaapp.core.domain.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverters
    fun fromGenreList(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverters
    fun toGenreList(genresString: String): List<Genre> {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(genresString, type)
    }
}