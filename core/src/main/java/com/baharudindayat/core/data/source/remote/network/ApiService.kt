package com.baharudindayat.core.data.source.remote.network

import com.baharudindayat.core.data.source.remote.response.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovie(
        @Header("Authorization") auth: String
    ) : MovieResponse
}