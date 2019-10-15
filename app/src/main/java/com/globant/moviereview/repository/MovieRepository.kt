package com.globant.moviereview.repository

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import com.globant.moviereview.R
import com.globant.moviereview.api.ApiService
import com.globant.moviereview.model.MovieDao
import com.globant.moviereview.model.MovieDatabase
import com.globant.moviereview.model.MovieResponse
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.utils.Constants.Companion.APIKEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * MovieRepository
 *
 * This Class returns the data to the MainActivity
 * a requestMovieReviewList method is created and asks if there is access to the internet performs a callback.enqueue,
 * through retrofit and populates the bd implemented with room
 *
 * @author juan.rendon
 */
class MovieRepository(private val context: Context) {

    private val apiService = ApiService.instance
    private val movieDatabase: MovieDao get() = MovieDatabase.getMovieDatabase(context).getMovieDAO()

    fun requestMovieReviewList(): List<MovieReview> {
        val networkInfo = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            apiService.getMovieReviewListFromInternet(APIKEY).enqueue(object : Callback<MovieResponse> {
                override fun onResponse(callMovieResponse: Call<MovieResponse>, response: Response<MovieResponse>) {
                    when (response.code()) {
                        200 -> insertMovieReviewListIntoDatabase(response)
                        else -> Toast.makeText(context, context.getString(R.string.no_movies), Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e(context.getString(R.string.error_001), t.printStackTrace().toString())
                    Toast.makeText(context, context.getString(R.string.no_response_from_server), Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(context, context.getString(R.string.no_connection), Toast.LENGTH_SHORT).show()
        }
        return MovieDatabase.getMovieDatabase(context).getMovieDAO().getMovieReview()
    }

    fun insertMovieReviewListIntoDatabase(response: Response<MovieResponse>) {
        response.body()?.let { movieResponse ->
            movieResponse.results.forEach { movieReview -> movieDatabase.insertMovieReview(movieReview) }
        }
    }
}
