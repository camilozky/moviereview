package com.globant.moviereview.repository

import com.globant.moviereview.model.MovieReview
import java.util.ArrayList

/**
 * ResponseInterface
 *
 * send of an type arrayListMovieReview response
 *
 * @author juan.rendon
 */

interface ResponseInterface {
    fun getListMovies(arrayListMovieReview: ArrayList<MovieReview>?)
}
