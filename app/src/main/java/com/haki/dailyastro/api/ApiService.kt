package com.haki.dailyastro.api

import com.haki.dailyastro.response.ApodResponse
import retrofit2.http.GET
import retrofit2.http.Query

//apod?api_key=wnM943J8CGUZaK7RNHdrRzKFyQ4vK2IYYXWPuBUm&start_date=2024-01-01&end_date=2024-01-03
interface ApiService {
    @GET("apod")
    suspend fun getStories(
        @Query("api_key") api_key: String,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
    ): ApodResponse
}