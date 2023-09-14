package com.baharudindayat.dynamicfavorite.di

import android.content.Context
import com.baharudindayat.dynamicfavorite.ui.FavoriteFragment
import com.baharudindayat.nontonin.di.FavoriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

        fun inject(activity: FavoriteFragment)

        @Component.Builder
        interface Builder {
            fun context(@BindsInstance context: Context): Builder
            fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
            fun build(): FavoriteComponent
        }
}