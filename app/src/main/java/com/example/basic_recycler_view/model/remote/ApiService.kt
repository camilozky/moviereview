package com.example.basic_recycler_view.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular?")
    fun getCurrentData(
            @Query("api_key") app_id: String
    ): Call<MovieResponse>
}
