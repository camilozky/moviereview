package com.example.basic_recycler_view.services

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie")
@Parcelize
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
        val overview: String? = null,
        @SerializedName("release_date")
        val releaseDate: String? = null
) : Parcelable
