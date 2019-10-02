package com.globant.moviereview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * MovieReview
 *
 * data class for the json that we get from themoviedb API in the field results
 * The following constants were created
 * VOTE_MAX
 * RATING_MAX
 * voteRule
 *
 * To have better maintenance when changing voteAverage
 *
 * @author juan.rendon
 */

@Entity(tableName = "movie")
data class MovieReview(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @SerializedName("popularity")
        val popularity: String? = null,
        @SerializedName("voteCount")
        val voteCount: String? = null,
        @SerializedName("video")
        val video: String? = null,
        @SerializedName("poster_path")
        val posterPath: String? = null,
        @SerializedName("adult")
        val adult: String? = null,
        @SerializedName("backdrop_path")
        val backdropPath: String? = null,
        @SerializedName("original_language")
        val originalLanguage: String? = null,
        @SerializedName("original_title")
        val originalTitle: String? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("vote_average")
        val voteAverage: String? = null,
        @SerializedName("overview")
        val summary: String? = null,
        @SerializedName("release_date")
        val releaseDate: String? = null
)

const val VOTE_MAX = 10
const val RATING_MAX = 5
const val voteRule = VOTE_MAX / RATING_MAX
