package com.haki.core.data.source.remote.network

import com.haki.core.data.source.remote.response.ApodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("apod")
    suspend fun getAstronomy(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): List<ApodResponse>
}
