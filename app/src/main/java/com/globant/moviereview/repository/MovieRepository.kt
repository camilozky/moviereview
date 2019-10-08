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
 * The data source is managed by means of an isConnected boolean that is passed as a direct parameter in the getListMovieReview method
 * A class called ConnectivityChecker was created that asks the OS if it has access the internet
 * An apiService variable of type ApiService is created and it is instantiated
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
    /**
     * getListMovieReview
     *
     * First search the database if there is no data, then look for the remote control and if there is no connection or failed response,
     * then show an error message to the user
     *
     * @author david.mazo
     */
    fun getListMovieReview(): List<MovieReview> {
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
                        else -> Toast.makeText(context, "There is not movies", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        } else {
            Toast.makeText(context, "There is not network connection", Toast.LENGTH_SHORT).show()
        }
        return getListMovieReviewDatabase()
    }

    /**
     * getListMovieReviewDatabase
     *
     * returns a query of the movie database movie list
     *
     * @author david.mazo
     */
    fun getListMovieReviewDatabase(): List<MovieReview> {
        return movieDatabase.getMovies()
    }

    /**
     * insertListMovieReviewDatabase
     *
     * insert each movie in the movies table with a loop
     *
     * @author david.mazo
     */
    fun insertListMovieReviewDatabase(response: Response<MovieResponse>) {
        response.body()?.let { movieResponse ->
            movieResponse.results.forEach { movieReview -> movieDatabase.insertMovie(movieReview) }
        }
    }

    /**
     * deleteListMovieReviewDatabase
     *
     * delete all the contents of the movies table
     *
     * @author david.mazo
     */
    fun deleteListMovieReviewDatabase() {
        return movieDatabase.deleteMovies()
    }
}
