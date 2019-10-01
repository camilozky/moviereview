package com.globant.moviereview.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globant.moviereview.model.remote.MovieReview
import java.util.ArrayList

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: ArrayList<MovieReview>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieReview)

    @Query("SELECT * from movie")
    fun getMovies(): List<MovieReview>

    @Query("SELECT * FROM movie WHERE movie.id=:id")
    fun getMovieDetail(id: Int): MovieReview
}
