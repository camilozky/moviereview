package com.example.basic_recycler_view.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") var id: Int = 0,
        @ColumnInfo(name = "popularity") var popularity: String = "",
        @ColumnInfo(name = "vote_count") var vote_count: String = "",
        @ColumnInfo(name = "video") var video: String = "",
        @ColumnInfo(name = "poster_path") var poster_path: String = "",
        @ColumnInfo(name = "adult") var adult: String = "",
        @ColumnInfo(name = "backdrop_path") var backdrop_path: String = "",
        @ColumnInfo(name = "original_language") var original_language: String = "",
        @ColumnInfo(name = "original_title") var original_title: String = "",
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "vote_average") var vote_average: String = "",
        @ColumnInfo(name = "overview") var overview: String = "",
        @ColumnInfo(name = "release_date") var release_date: String = ""
)
