package com.globant.moviereview.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.globant.moviereview.R
import com.globant.moviereview.api.ApiService
import com.globant.moviereview.model.MovieDao
import com.globant.moviereview.model.MovieResponse
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.model.MovieReviewDatabase
import com.globant.moviereview.utils.APIKEY
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
    private val movieDatabase: MovieDao get() = MovieReviewDatabase.getMovieDatabase(context).getMovieDAO()

    fun requestMovieReviewList(): List<MovieReview> {
        apiService.getMovieReviewListFromInternet(APIKEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(callMovieResponse: Call<MovieResponse>, response: Response<MovieResponse>) {
                when (response.code()) {
                    200 -> insertMovieReviewListIntoDatabase(response)
                    else -> Toast.makeText(context, context.getString(R.string.no_movies), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(context.getString(R.string.error_001), t.printStackTrace().toString())
            }
        })
        return MovieReviewDatabase.getMovieDatabase(context).getMovieDAO().getMovieReviewList()
    }

    fun insertMovieReviewListIntoDatabase(response: Response<MovieResponse>) {
        response.body()?.apply {
            for (movieReview: MovieReview in this.results) {
                movieDatabase.insertMovieReview(movieReview)
            }
        }
    }
}
