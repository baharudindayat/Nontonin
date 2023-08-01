package com.baharudindayat.core.domain.repository

import com.baharudindayat.core.data.Resource
import com.baharudindayat.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}