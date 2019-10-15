package com.globant.moviereview.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * MovieReviewDatabase
 *
 * We can use it to create other classes
 * It is abstract because it is implemented by Room
 *
 * @author david.mazo
 */
@Database(entities = [MovieReview::class], version = 1, exportSchema = false)
abstract class MovieReviewDatabase : RoomDatabase() {

    abstract fun getMovieDAO(): MovieDao

    companion object {
        fun getMovieDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
                MovieReviewDatabase::class.java, "MovieReviewDatabase")
                .allowMainThreadQueries()
                .build()
    }
}
