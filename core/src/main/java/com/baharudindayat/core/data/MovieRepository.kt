package com.baharudindayat.core.data

import com.baharudindayat.core.data.source.local.LocalDataSource
import com.baharudindayat.core.data.source.remote.RemoteDataSource
import com.baharudindayat.core.data.source.remote.network.ApiResponse
import com.baharudindayat.core.data.source.remote.response.movie.ResultsItem
import com.baharudindayat.core.domain.model.Movie
import com.baharudindayat.core.domain.repository.MoviesRepository
import com.baharudindayat.core.utils.AppExecutors
import com.baharudindayat.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MoviesRepository {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}