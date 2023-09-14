package com.baharudindayat.dynamicfavorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.baharudindayat.core.domain.usecase.MovieUseCase


class FavoriteViewModel constructor(movieUseCase: MovieUseCase):ViewModel(){
    val movie = movieUseCase.getFavoriteMovie().asLiveData()
}