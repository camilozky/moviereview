package com.example.basic_recycler_view.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basic_recycler_view.services.ApiMovie
import java.util.ArrayList

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: ArrayList<ApiMovie>?)

    @Query("SELECT * from movie")
    fun getMovies(): List<ApiMovie>
}
