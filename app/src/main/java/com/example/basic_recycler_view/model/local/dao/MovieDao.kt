package com.example.basic_recycler_view.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basic_recycler_view.model.remote.MovieReview
import java.util.ArrayList

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovie(movie: ArrayList<MovieReview>?)

    @Query("SELECT * from movie")
    fun getMovies(): List<MovieReview>
}
