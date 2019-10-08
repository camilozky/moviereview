package com.globant.moviereview.model

import com.google.gson.annotations.SerializedName

/**
 * MovieResponse
 *
 * class for the nested json that we get from the api call of themoviedb
 * it was created as an List type that in turn has another kind of json template called MovieReview
 *
 * @author juan.rendon
 */
class MovieResponse {
    @SerializedName("results")
    var results: List<MovieReview> = listOf()
}
