package com.globant.moviereview.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * MovieDao
 * facilitates access to stored data with the following methods
 * insertMovies -> inserts a list of all movies
 * insertMovie -> insert a movie
 * getMovies -> consult a list of all the movies
 * getMovieDetail -> consult a movie
 *
 * @author david.mazo
 */

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieReview)

    @Query("DELETE from movies")
    fun deleteMovies()

    @Query("SELECT * from movies")
    fun getMovies(): List<MovieReview>

    @Query("SELECT * FROM movies WHERE movies.id=:id")
    fun getMovieDetail(id: Int): MovieReview
}
