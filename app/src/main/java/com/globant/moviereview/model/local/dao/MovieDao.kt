package com.globant.moviereview.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globant.moviereview.model.remote.MovieReview
import java.util.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: ArrayList<MovieReview>?)

    @Query("SELECT * from movie")
    fun getMovies(): List<MovieReview>
}