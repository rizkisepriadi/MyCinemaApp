package com.app.mycinemaapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.mycinemaapp.core.domain.model.Genre

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "originalTitle")
    val originalTitle: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "posterUrl")
    val posterUrl: String,

    @ColumnInfo(name = "backdropUrl")
    val backdropUrl: String?,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,

    @ColumnInfo(name = "voteAverage")
    val rating: Double,

    @ColumnInfo(name = "voteCount")
    val genres: String,

    @ColumnInfo(name = "runtime")
    val runtime: Int,

    @ColumnInfo(name = "language")
    val language: String,

    @ColumnInfo(name = "tagline")
    val tagline: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "homepage")
    val homepage: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
)