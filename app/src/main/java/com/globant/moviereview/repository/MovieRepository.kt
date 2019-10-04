package com.globant.moviereview.repository

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.globant.moviereview.api.ApiService
import com.globant.moviereview.model.MovieDatabase
import com.globant.moviereview.model.MovieResponse
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.utils.ConnectivityChecker
import com.globant.moviereview.utils.Constants.Companion.APIKEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * MovieRepository
 *
 * The data source is managed by means of an isConnected boolean that is passed as a direct parameter in the getData method
 * A class called ConnectivityChecker was created that asks the OS if it has access the internet
 * An apiService variable of type ApiService is created and it is instantiated
 * a getData method is created and asks if there is access to the internet performs a callback.enqueue, through retrofit and populates the bd
 * implemented with room
 *
 * @author juan.rendon
 */

class MovieRepository(private val responseInterface: ResponseInterface) {

    private val apiService: ApiService = ApiService.instance

    fun getData(context: Context) {
        if (!ConnectivityChecker(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isConnected) {
            if (getMovieList(context).isNotEmpty())
                responseInterface.getListMovies(getMovieList(context))
            else Toast.makeText(context, "There is not movies", Toast.LENGTH_SHORT).show()
        } else {
            val call = apiService.getCurrentData(APIKEY)
            call.enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    when (response.code()) {
                        200 -> {
                            sendResult(response, context)
                            if (getMovieList(context).isNotEmpty())
                                responseInterface.getListMovies(getMovieList(context))
                            else Toast.makeText(context, "There is not movies", Toast.LENGTH_SHORT).show()
                        }
                        else -> Toast.makeText(context, "There is not movies", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    private fun sendResult(response: Response<MovieResponse>?, context: Context) {
        response?.body()?.let { movieResponse ->
            movieResponse.results?.forEach { movieReview -> MovieDatabase.getDatabase(context).getMovieDAO().insertMovie(movieReview) }
        }
    }

    private fun getMovieList(context: Context): ArrayList<MovieReview> {
        return ArrayList(MovieDatabase.getDatabase(context).getMovieDAO().getMovies())
    }

}
