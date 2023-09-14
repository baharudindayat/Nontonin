package com.baharudindayat.core.data.source.remote

import android.util.Log
import com.baharudindayat.core.BuildConfig
import com.baharudindayat.core.data.source.remote.network.ApiResponse
import com.baharudindayat.core.data.source.remote.network.ApiService
import com.baharudindayat.core.data.source.remote.response.movie.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService){
    suspend fun getAllMovie(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val auth = BuildConfig.API_KEY
                val response = apiService.getMovie(auth)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}