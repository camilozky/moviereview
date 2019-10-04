package com.globant.moviereview.repository

import com.globant.moviereview.model.MovieReview
import java.util.*

/**
 * ResponseInterface
 *
 * send of an type arrayListMovieReview response
 *
 * @author juan.rendon
 */
//TODO maybe deleted this class with arguments
interface ResponseInterface {
    fun getListMovies(arrayListMovieReview: ArrayList<MovieReview>?)
}
