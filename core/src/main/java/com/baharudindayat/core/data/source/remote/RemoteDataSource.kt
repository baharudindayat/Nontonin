package com.baharudindayat.core.data.source.remote

import android.util.Log
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
        //get data from remote api
        return flow {
            try {
                val auth = "bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3OGI1ZWFhOTE3NjI2ZjFhMDljOWJkZWFhZDBiODU5MyIsInN1YiI6IjY0YzMzNzgyYWY2ZTk0MDExZDZlZjFiYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.t_94nSQJalNyLMW3qeUw3mhSWuOAEldviKU35jRb9U4"
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