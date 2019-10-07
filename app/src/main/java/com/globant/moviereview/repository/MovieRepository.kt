package com.globant.moviereview.repository

import android.content.Context
import android.widget.Toast
import com.globant.moviereview.api.ApiService
import com.globant.moviereview.model.MovieDao
import com.globant.moviereview.model.MovieDatabase
import com.globant.moviereview.model.MovieResponse
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.utils.Constants.Companion.APIKEY
import com.globant.moviereview.utils.hasConnection
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

class MovieRepository(private val context: Context) {

    private val apiService: ApiService = ApiService.instance
    private val movieDao: MovieDao = MovieDatabase.getDatabase(context).getMovieDAO()

    fun getData(): List<MovieReview> {

        var moviesDB = movieDao.getMovies()

        if (context.hasConnection()) {
            val call = apiService.getCurrentData(APIKEY)
            call.enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    when (response.code()) {
                        200 -> {
                            if (moviesDB.isNotEmpty()) {
                                //TODO: create the delete option within DAO
//                                moviesDB= movieDao.deleteMovies()
                            }
                            insertIntoDB(response)
                            moviesDB = movieDao.getMovies()
                        }
                        //TODO: Ask info to database
                        //TODO:
                        //TODO: delete class connectivity checker
                        //
                        else -> Toast.makeText(
                            context,
                            "There is not movies",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        } else {
            Toast.makeText(context, "There is not movies", Toast.LENGTH_SHORT).show()
        }
        return movieDao.getMovies()
    }

    fun getDBMovieList(): List<MovieReview> {
        return movieDao.getMovies()
    }

    private fun insertIntoDB(response: Response<MovieResponse>) {
        response.body()?.let { movieResponse ->
            movieResponse.results.forEach { movieReview -> movieDao.insertMovie(movieReview) }
        }
    }
}
