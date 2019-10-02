package com.globant.moviereview.repository

import android.content.Context
import android.net.ConnectivityManager
import com.globant.moviereview.api.ApiService
import com.globant.moviereview.model.MovieDatabase
import com.globant.moviereview.model.MovieResponse
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.utils.ConnectivityChecker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * MovieRepository
 *
 * The data source is managed by means of an isConnected boolean that is passed as a direct parameter in the getData method
 * A class called ConnectivityChecker was created that asks the OS if it has access the internet
 * An apiService variable of type ApiService is created and it is instantiated
 * a getData method is created and asks if there is access to the internet performs a callback.enqueue through retrofit and populates the bd implemented with room
 *
 * @author juan.rendon
 */

class MovieRepository(private val responseInterface: ResponseInterface) {

    private val apiService: ApiService = ApiService.instance

    fun getData(context: Context) {
        if (!ConnectivityChecker(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isConnected) {
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
