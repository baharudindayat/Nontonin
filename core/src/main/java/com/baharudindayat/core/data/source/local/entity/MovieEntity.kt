package com.baharudindayat.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: Int,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "popularity")
    val popularity: Float,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Float,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
