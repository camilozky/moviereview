package com.globant.moviereview.utils

import com.globant.moviereview.model.MovieReview

/**
 * MovieReviewEvents
 *
 * The onItemClicked method is created to be implemented in the classes that use the interface
 *
 * @author juan.rendon
 */

interface MovieReviewEvents {
    fun onItemClicked(movieReview: MovieReview)
}
