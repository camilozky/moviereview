package com.globant.moviereview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.globant.moviereview.ui.MainActivity
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
        val popularity: String = "",
        @SerializedName("voteCount")
        val voteCount: String = "",
        @SerializedName("video")
        val video: String = "",
        @SerializedName("poster_path")
        val posterPath: String = "",
        @SerializedName("adult")
        val adult: String = "",
        @SerializedName("backdrop_path")
        val backdropPath: String = "",
        @SerializedName("original_language")
        val originalLanguage: String = "",
        @SerializedName("original_title")
        val originalTitle: String = "",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("vote_average")
        val voteAverage: String = "",
        @SerializedName("overview")
        val summary: String = "",
        @SerializedName("release_date")
        val releaseDate: String = ""
)

fun returnFactorMovieRating(): Int {
    return MainActivity.VOTE_MAX / MainActivity.RATING_MAX
}
