package com.globant.moviereview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * MovieReview
 *
 * data class for the json that we get from themoviedb API in the field results
 *
 * @author juan.rendon
 */
@Entity(tableName = "movie_review")
data class MovieReview(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int,
        var video: String?,
        var adult: String?,
        var title: String?,
        var popularity: String?,
        @SerializedName("overview")
        var summary: String?,
        @SerializedName("vote_count")
        var voteCount: Float,
        @SerializedName("poster_path")
        var posterPath: String?,
        @SerializedName("release_date")
        var releaseDate: String?,
        @SerializedName("vote_average")
        var voteAverage: String,
        @SerializedName("backdrop_path")
        var backdropPath: String?,
        @SerializedName("original_title")
        var originalTitle: String?,
        @SerializedName("original_language")
        var originalLanguage: String?)
