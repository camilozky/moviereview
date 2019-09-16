package com.example.basic_recycler_view.services

import com.google.gson.annotations.SerializedName

class ApiMovie {
    @SerializedName("popularity")
    val popularity: String? = null
    @SerializedName("vote_count")
    val vote_count: String? = null
    @SerializedName("video")
    val video: String? = null
    @SerializedName("poster_path")
    val poster_path: String? = null
    @SerializedName("adult")
    val adult: String? = null
    @SerializedName("backdrop_path")
    val backdrop_path: String? = null
    @SerializedName("original_language")
    val original_language: String? = null
    @SerializedName("original_title")
    val original_title: String? = null
    @SerializedName("genre_ids")
    val genre_ids: List<Int>? = null
    @SerializedName("title")
    val title: String? = null
    @SerializedName("vote_average")
    val vote_average: String? = null
    @SerializedName("overview")
    val overview: String? = null
    @SerializedName("release_date")
    val release_date: String? = null
}
