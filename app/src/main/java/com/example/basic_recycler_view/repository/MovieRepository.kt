package com.example.basic_recycler_view.repository

import android.content.Context
import com.example.basic_recycler_view.model.local.db.MovieDatabase
import com.example.basic_recycler_view.model.remote.ApiService
import com.example.basic_recycler_view.model.remote.MovieResponse
import com.example.basic_recycler_view.model.remote.MovieReview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class MovieRepository(private val responseInterface: ResponseInterface) {

    interface ResponseInterface {
        fun sendResponse(arrayListMovieReview: ArrayList<MovieReview>?)
    }

    var isLoadingData: Boolean = false
    var hasConnection: Boolean = false
    private val retrofit: Retrofit
    private val apiService: ApiService

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getData() {
        if (hasConnection) {
            val movieDatabase = MovieDatabase.getDatabase(responseInterface as Context)
            val listMovieReview = movieDatabase.movieDAO().getMovies()
            localDataResponse(ArrayList(listMovieReview))
            isLoadingData = false

        } else {
            val call = apiService.getCurrentData(APIKEY)
            isLoadingData = true

            call.enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.code() == 200) {
                        networkResponse(response)
                    } else {
                        networkResponse(null)
                    }
                    isLoadingData = false
                    hasConnection = true
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                    networkResponse(null)
                    isLoadingData = false
                    hasConnection = false
                }
            })
        }
    }

    private fun networkResponse(response: Response<MovieResponse>?) {
        response?.body()?.let { response ->
            responseInterface.sendResponse(response.results)
        }
    }

    private fun localDataResponse(response: ArrayList<MovieReview>?) {
        responseInterface.sendResponse(response)
    }

    companion object {
        var BASEURL = "https://api.themoviedb.org/3/"
        var APIKEY = "99ac1d44af506e889c0cb61a2ef3fa22"
    }
}
