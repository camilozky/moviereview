package com.globant.moviereview.ui

import com.globant.moviereview.model.remote.MovieReview

interface MovieReviewEvents {
    fun onItemClicked(movieReview: MovieReview)
}
