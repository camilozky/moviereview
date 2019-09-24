package com.example.basic_recycler_view.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.basic_recycler_view.services.ApiMovie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getMovieList(): List<ApiMovie>?

    @Query("SELECT * FROM movie WHERE movie.id= :id")
    suspend fun getMovie(id: String): ApiMovie?

    @Insert(onConflict = IGNORE)
    suspend fun insert(movie: ApiMovie)

    @Insert(onConflict = IGNORE)
    suspend fun insert(list: List<ApiMovie>)

    @Insert(onConflict = REPLACE)
    suspend fun update(movie: ApiMovie)

    @Delete
    suspend fun deleteMovie(movie: ApiMovie)

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteMovie(id: String)

    @Query("DELETE FROM movie")
    suspend fun deleteAll()

    @Query("SELECT * FROM movie LIMIT :pageSize OFFSET :pageIndex")
    suspend fun getMoviePage(pageSize: Int, pageIndex: Int): List<ApiMovie>?
}
