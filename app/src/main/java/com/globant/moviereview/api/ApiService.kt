package com.globant.moviereview.api

import com.globant.moviereview.model.remote.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular?")
    fun getCurrentData(
            @Query("api_key") app_id: String
    ): Call<MovieResponse>
}
