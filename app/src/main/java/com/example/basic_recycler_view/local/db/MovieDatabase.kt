package com.example.basic_recycler_view.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basic_recycler_view.local.dao.MovieDao

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDao

    companion object {

        @JvmStatic
        fun getDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
                MovieDatabase::class.java, "MovieDatabase")
                .build()
    }
}
