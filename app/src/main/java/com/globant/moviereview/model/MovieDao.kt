package com.globant.moviereview.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * MovieDao
 *
 * to Access Database @Entity(tableName = "movies") data class MovieReview
 * facilitates access to stored data with the following methods
 *
 * @author david.mazo
 */
@Dao
interface MovieDao {
    /**
     * insertMovie -> insert a movie into Database movies
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieReview)

    /**
     * getMovies -> consult a list of all the movies
     */
    @Query("SELECT * from movies")
    fun getMovies(): List<MovieReview>

    /**
     * getMovieDetail -> consult a movie
     */
    @Query("SELECT * FROM movies WHERE movies.id=:id")
    fun getMovieDetail(id: Int): MovieReview

    /**
     * deleteMovies-> delete all rows
     */
    @Query("DELETE from movies")
    fun deleteMovies()
}
