package com.globant.moviereview.api

import com.globant.moviereview.model.MovieResponse
import com.globant.moviereview.utils.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ApiService
 * provide accessor implementations themoviedb
 * retrofit service is instantiated by means of a companion object
 * themoviedb API key is passed as a parameter in the getCurrentDate method
 * returns a call with list MovieResponse class
 *
 * @author juan.rendon
 */

interface ApiService {
    /**
     * getMovieReviewListFromInternet
     * In this interface the token or api_key of themoviedb.com URL is passed as a parameter and returns a call of type MovieResponse
     */
    @GET("movie/popular?")
    fun getMovieReviewListFromInternet(@Query("api_key") app_id: String): Call<MovieResponse>

    companion object {
        val instance: ApiService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}
