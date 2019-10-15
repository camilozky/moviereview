package com.globant.moviereview.utils

/**
 * Constants
 *
 * In this class all the constants that are needed in the application are declared
 *
 * @author juan.rendon
 */
open class Constants {
    companion object {
        const val LINEAR_LAYOUT: Int = 1
        const val GRILL_LAYOUT: Int = 2
        const val STAGGERED_LAYOUT: Int = 3
        const val ID_MOVIE: String = "idMovie"
        const val BASEURL = "https://api.themoviedb.org/3/"
        const val APIKEY = "99ac1d44af506e889c0cb61a2ef3fa22"
        const val VOTE_MAX = 10
        const val RATING_MAX = 5
    }
}

/**
 * getFactorMovieReviewRating
 *
 * with this method the factor is returned so that it is divided by vote
 */
fun getFactorMovieReviewRating(): Int {
    return Constants.VOTE_MAX / Constants.RATING_MAX
}
