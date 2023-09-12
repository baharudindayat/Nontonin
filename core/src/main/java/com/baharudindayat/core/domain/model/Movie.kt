package com.baharudindayat.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Float,
    val voteAverage: Float,
    val id: Int,
    val adult: Boolean,
    val voteCount: Int,
    var isFavorite: Boolean
) : Parcelable