package com.example.basic_recycler_view.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.basic_recycler_view.local.dao.MovieDao
import com.example.basic_recycler_view.services.ApiMovie

@Database(entities = [ApiMovie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
