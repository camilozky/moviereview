package com.globant.moviereview.repository

import android.content.Context
import com.globant.moviereview.api.ApiService
import com.globant.moviereview.model.local.db.MovieDatabase
import com.globant.moviereview.model.remote.MovieResponse
import com.globant.moviereview.model.remote.MovieReview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieRepository(private val responseInterface: ResponseInterface) {

    private val apiService: ApiService = ApiService.instance

    fun getData(isConnected: Boolean) {
        if (!isConnected) {
            val movieDatabase = MovieDatabase.getDatabase(responseInterface as Context)
            val listMovieReview = movieDatabase.getMovieDAO().getMovies()
            getLocalDataResponse(ArrayList(listMovieReview))
        } else {
            val call = apiService.getCurrentData(APIKEY)
            call.enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                ) {
                    if (response.code() == 200) {
                        getNetworkResponse(response)
                    } else {
                        getNetworkResponse(null)
                    }
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    private fun getNetworkResponse(response: Response<MovieResponse>?) {
        response?.body()?.let { response ->
            responseInterface.sendResponse(response.results)
        }
    }

    private fun getLocalDataResponse(response: ArrayList<MovieReview>?) {
        responseInterface.sendResponse(response)
    }

    companion object {
        var BASEURL = "https://api.themoviedb.org/3/"
        var APIKEY = "99ac1d44af506e889c0cb61a2ef3fa22"
    }
}
