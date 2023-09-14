package com.baharudindayat.dynamicfavorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baharudindayat.core.domain.usecase.MovieUseCase
import com.baharudindayat.dynamicfavorite.ui.FavoriteViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val movieUseCase: MovieUseCase):
    ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            when {
                modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                    FavoriteViewModel(movieUseCase) as T
                }
                else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
            }
    }