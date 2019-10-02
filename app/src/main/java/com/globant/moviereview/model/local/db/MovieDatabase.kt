package com.globant.moviereview.model.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.globant.moviereview.model.local.dao.MovieDao
import com.globant.moviereview.model.remote.MovieReview

/**
 * MovieDatabase
 *
 * We can use it to create other classes
 * It is abstract because it is implemented by Room
 *
 * @author david.mazo
 */

@Database(entities = [MovieReview::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDAO(): MovieDao

    companion object {
        fun getDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
                MovieDatabase::class.java, "MovieDatabase").allowMainThreadQueries()
                .build()
    }
}
