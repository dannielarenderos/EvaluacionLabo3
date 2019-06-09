package com.example.evaluacionlabo3.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.evaluacionlabo3.database.entities.Movie


@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM movie")
    suspend fun nukeTable()
}