package com.baharudindayat.core.utils

import com.baharudindayat.core.data.source.local.entity.MovieEntity
import com.baharudindayat.core.data.source.remote.response.movie.ResultsItem
import com.baharudindayat.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                voteAverage = it.voteAverage,
                adult = it.adult,
                backdropPath = it.backdropPath,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                isFavorite = false

            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                voteAverage = it.voteAverage,
                adult = it.adult,
                backdropPath = it.backdropPath,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        voteAverage = input.voteAverage,
        adult = input.adult,
        backdropPath = input.backdropPath,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        overview = input.overview,
        popularity = input.popularity,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )





}