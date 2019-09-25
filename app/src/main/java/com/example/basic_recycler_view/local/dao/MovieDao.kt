package com.example.basic_recycler_view.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basic_recycler_view.local.db.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovie(movie: ArrayList<MovieEntity>?)

    @Query("SELECT * from movie")
    fun getMovies(): List<MovieEntity>
}
