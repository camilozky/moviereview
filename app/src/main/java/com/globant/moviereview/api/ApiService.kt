package com.globant.moviereview.api

import com.globant.moviereview.model.remote.MovieResponse
import com.globant.moviereview.repository.MovieRepository
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular?")
    fun getCurrentData(
            @Query("api_key") app_id: String
    ): Call<MovieResponse>

    companion object {
        val instance: ApiService = Retrofit.Builder()
                .baseUrl(MovieRepository.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}

