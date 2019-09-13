package com.example.basic_recycler_view.model

data class Item(
        val id: Int,
        val popularity: String?,
        val vote_count: String?,
        val video: String?,
        val poster_path: String?,
        val adult: String?,
        val backdrop_path: String?,
        val original_language: String?,
        val original_title: String?,
        val genre_ids: String?,
        val title: String?,
        val vote_average: String?,
        val overview: String?,
        val release_date: String?
)
