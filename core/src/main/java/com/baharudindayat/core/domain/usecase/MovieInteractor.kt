package com.baharudindayat.core.domain.usecase

import com.baharudindayat.core.domain.model.Movie
import com.baharudindayat.core.domain.repository.MoviesRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val moviesRepository: MoviesRepository): MovieUseCase{
    override fun getAllMovie() = moviesRepository.getAllMovie()

    override fun getFavoriteMovie() = moviesRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = moviesRepository.setFavoriteMovie(movie, state)
}