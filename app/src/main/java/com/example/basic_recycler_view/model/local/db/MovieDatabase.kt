package com.example.basic_recycler_view.model.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basic_recycler_view.model.local.dao.MovieDao
import com.example.basic_recycler_view.model.remote.MovieReview

@Database(entities = [MovieReview::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDao

    companion object {
        @JvmStatic
        fun getDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
                MovieDatabase::class.java, "MovieDatabase").allowMainThreadQueries()
                .build()
    }
}
