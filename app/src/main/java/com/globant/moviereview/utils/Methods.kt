package com.globant.moviereview.utils

/**
 * Methods
 *
 * returns the factor to qualify on a scale of 1 to 5
 *
 * @author david.maxo
 */
fun getFactorMovieReviewRating(): Int {
    return Constants.VOTE_MAX / Constants.RATING_MAX
}
