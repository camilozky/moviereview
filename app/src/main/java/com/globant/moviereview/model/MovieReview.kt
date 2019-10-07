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

@Entity(tableName = "movies")
data class MovieReview(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
        @SerializedName("popularity") val popularity: String = "",
        @SerializedName("video") val video: String = "",
        @SerializedName("poster_path") val posterPath: String = "",
        @SerializedName("adult") val adult: String = "",
        @SerializedName("backdrop_path") val backdropPath: String = "",
        @SerializedName("original_language") val originalLanguage: String = "",
        @SerializedName("original_title") val originalTitle: String = "",
        @SerializedName("title") val title: String = "",
        @SerializedName("vote_average") val voteAverage: String = "",
        @SerializedName("overview") val summary: String = "",
        @SerializedName("release_date") val releaseDate: String = ""
)
