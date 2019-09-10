package com.example.basic_recycler_view.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonService {
    @GET("apod?")
    fun getCurrentData(
            @Query("api_key") app_id: String
    ): Call<JsonResponse>
}
