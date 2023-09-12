package com.baharudindayat.nontonin.presentation.ui.detail

import androidx.lifecycle.ViewModel
import com.baharudindayat.core.domain.model.Movie
import com.baharudindayat.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFilmViewModel @Inject constructor(private val filmUseCase: MovieUseCase): ViewModel(){
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        filmUseCase.setFavoriteMovie(movie, newStatus)
}