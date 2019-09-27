package com.globant.moviereview.model.remote

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("results")
    val results: ArrayList<MovieReview>? = null
}
