package com.example.basic_recycler_view.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("apod?")
    fun getCurrentData(
            @Query("api_key") app_id: String,
            @Query("date") date: String
    ): Call<ApiResponse>
}
