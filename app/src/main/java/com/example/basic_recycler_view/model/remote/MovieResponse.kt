package com.example.basic_recycler_view.model.remote

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("page")
    val page: Int = 0
    @SerializedName("total_results")
    val total_results: Double = 0.0
    @SerializedName("total_pages")
    val total_pages: Double = 0.0
    @SerializedName("results")
    val results: ArrayList<MovieReview>? = null
}
