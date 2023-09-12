package com.baharudindayat.core.di

import android.content.Context
import androidx.room.Room
import com.baharudindayat.core.data.source.local.room.MovieDao
import com.baharudindayat.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java, "Nontonin.db"
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    fun provideTourismDao(database: MovieDatabase): MovieDao = database.MovieDao()

}