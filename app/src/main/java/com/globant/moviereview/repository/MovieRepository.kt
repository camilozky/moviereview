package com.globant.moviereview.repository

import android.content.Context
import android.widget.Toast
import com.globant.moviereview.api.ApiService
import com.globant.moviereview.model.MovieDao
import com.globant.moviereview.model.MovieDatabase
import com.globant.moviereview.model.MovieResponse
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.ui.MainActivity
import com.globant.moviereview.utils.Constants.Companion.APIKEY
import com.globant.moviereview.utils.hasConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * MovieRepository
 *
 * This Class return the data to the MainActivity
 * a getListMovieReview method is created and asks if there is access to the internet performs a callback.enqueue, through retrofit and populates the bd
 * implemented with room
 *
 * @author juan.rendon
 */
class MovieRepository {

    private var context: Context
    private val apiService = ApiService.instance

    constructor(context: MainActivity) {
        this.context = context
    }

    private val movieDatabase: MovieDao get() = MovieDatabase.getMovieDatabase(context).getMovieDAO()

    fun getListMovieReview() {
        if (context.hasConnection()) {
            val callMovieResponse = apiService.getListMovieReviewNetwork(APIKEY)
            callMovieResponse.enqueue(object : Callback<MovieResponse> {
                override fun onResponse(callMovieResponse: Call<MovieResponse>, response: Response<MovieResponse>) {
                    when (response.code()) {
                        200 -> {
                            if (getListMovieReviewDatabase().isNotEmpty()) {
                                deleteListMovieReviewDatabase()
                            }
                            insertListMovieReviewDatabase(response)
                        }
                        else -> Toast.makeText(context, "There are not movies", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("Error#001", "Error $t.printStackTrace()")
                    Toast.makeText(context, "There was an error trying to get the list movies from remote server", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(context, "There is not network connection", Toast.LENGTH_SHORT).show()
        }
    }

    fun getListMovieReviewDatabase(): List<MovieReview> {
        return movieDatabase.getMovies()
    }

    fun insertListMovieReviewDatabase(response: Response<MovieResponse>) {
        response.body()?.let { movieResponse ->
            movieResponse.results.forEach { movieReview -> movieDatabase.insertMovie(movieReview) }
        }
    }

    fun deleteListMovieReviewDatabase() {
        return movieDatabase.deleteMovies()
    }
}
