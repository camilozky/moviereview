package com.example.basic_recycler_view.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular?")
    fun getCurrentData(
            @Query("api_key") app_id: String
    ): Call<ApiResponse>

    @GET("movie?")
    fun getCurrentDataDetail(
            @Query("api_key") app_id: String,
            @Query("movie_id") movie_id: String
    ): Call<ApiMovieDetail>
}
