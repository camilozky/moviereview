package com.globant.moviereview.repository

import com.globant.moviereview.model.remote.MovieReview
import java.util.*

interface ResponseInterface {
    fun sendResponse(arrayListMovieReview: ArrayList<MovieReview>?)
}