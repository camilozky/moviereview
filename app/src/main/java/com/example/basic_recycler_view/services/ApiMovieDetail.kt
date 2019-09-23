package com.example.basic_recycler_view.services

import com.google.gson.annotations.SerializedName

data class ApiMovieDetail(

        @SerializedName("adult")
        val adult: String? = null,
        @SerializedName("backdrop_path")
        val backdrop_path: String? = null,
        @SerializedName("belongs_to_collection")
        val belongs_to_collection: String? = null,
        @SerializedName("budget")
        val budget: String? = null,
        @SerializedName("homepage")
        val homepage: String? = null,
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("imdb_id")
        val imdb_id: String? = null,
        @SerializedName("original_language")
        val original_language: String? = null,
        @SerializedName("original_title")
        val original_title: String? = null,
        @SerializedName("overview")
        val overview: String? = null,
        @SerializedName("popularity")
        val popularity: String? = null,
        @SerializedName("poster_path")
        val poster_path: String? = null,
        @SerializedName("release_date")
        val release_date: String? = null,
        @SerializedName("revenue")
        val revenue: String? = null,
        @SerializedName("runtime")
        val runtime: String? = null,
        @SerializedName("tagline")
        val tagline: String? = null
)
