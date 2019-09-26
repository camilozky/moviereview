package com.example.basic_recycler_view.repository

import android.content.Context
import com.example.basic_recycler_view.model.data.db.MovieDatabase
import com.example.basic_recycler_view.model.data.remote.ApiService
import com.example.basic_recycler_view.model.data.remote.MovieResponse
import com.example.basic_recycler_view.model.data.remote.MovieReview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList


class MovieRepository(listeningActivity: ResponseInterface) {

    interface ResponseInterface {
        fun sendResponse(response: ArrayList<MovieReview>?)
    }

    var isLoadingData: Boolean = false
    private val responseListener: ResponseInterface = listeningActivity
    private val retrofit: Retrofit
    private var hasConnection: Boolean = false
    private val service: ApiService

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(ApiService::class.java)
    }

    fun getData() {

        if (hasConnection) {
            val databaseMovie = MovieDatabase.getDatabase(responseListener as Context)
            val tempArray = databaseMovie.movieDAO().getMovies()
            itemLocalResponse(ArrayList(tempArray))
            isLoadingData = false


        } else {
            val call = service.getCurrentData(AppId)
            isLoadingData = true

            call.enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.code() == 200) {
                        itemResponse(response)
                    } else {
                        itemResponse(null)
                    }
                    isLoadingData = false
                    hasConnection = true
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                    itemResponse(null)
                    isLoadingData = false
                    hasConnection = false
                }
            })
        }
    }

    private fun itemResponse(apiResponse: Response<MovieResponse>?) {

        apiResponse?.body()?.let { response ->
            responseListener.sendResponse(response.results)
        }
    }

    private fun itemLocalResponse(localResponse: ArrayList<MovieReview>?) {
        responseListener.sendResponse(localResponse)
    }

    companion object {
        var BaseUrl = "https://api.themoviedb.org/3/"
        var AppId = "99ac1d44af506e889c0cb61a2ef3fa22"
    }
}
