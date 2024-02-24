package com.haki.core.data.source.remote

import android.util.Log
import com.haki.core.data.source.remote.network.ApiResponse
import com.haki.core.data.source.remote.network.ApiService
import com.haki.core.data.source.remote.response.ApodResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllAstronomy(
        startDate: String,
        endDate: String
    ): Flow<ApiResponse<List<ApodResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getAstronomy(
                    "wnM943J8CGUZaK7RNHdrRzKFyQ4vK2IYYXWPuBUm",
                    startDate,
                    endDate
                )
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

