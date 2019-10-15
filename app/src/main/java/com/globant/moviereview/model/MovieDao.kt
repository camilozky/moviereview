package com.globant.moviereview.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * MovieDao
 *
 * to Access Database @Entity(tableName = "movie_review") data class MovieReview
 * facilitates access to stored data with the following methods
 *
 * @author david.mazo
 */
@Dao
interface MovieDao {
    /**
     * insertMovieReview -> insert a registry of movie_review into Database movie_review
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieReview(movie_review: MovieReview)

    /**
     * getMovieReview -> consult a list of all the movie_review
     */
    @Query("SELECT * from movie_review")
    fun getMovieReview(): List<MovieReview>

    /**
     * getMovieReviewDetail -> consult a movie_review
     */
    @Query("SELECT * FROM movie_review WHERE movie_review.id=:id")
    fun getMovieReviewDetail(id: Int): MovieReview
}
