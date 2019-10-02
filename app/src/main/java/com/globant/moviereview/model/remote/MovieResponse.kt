package com.globant.moviereview.model.remote

import com.google.gson.annotations.SerializedName

/**
 * MovieResponse
 *
 * class for the nested json that we get from the api call of themoviedb
 * it was created as an ArrayList type that in turn has another kind of json template called MovieReview
 *
 * @author juan.rendon
 */

class MovieResponse {
    @SerializedName("results")
    val results: ArrayList<MovieReview>? = null
}
