package com.baharudindayat.nontonin.presentation.ui.film

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.baharudindayat.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}