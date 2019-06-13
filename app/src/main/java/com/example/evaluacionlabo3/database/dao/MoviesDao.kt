package com.example.evaluacionlabo3.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.evaluacionlabo3.database.entities.Movie
import com.example.evaluacionlabo3.retrofit.MovieDetails


@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM movie")
    suspend fun nukeTable()

    @Update
    suspend fun update(movie: Movie)

    @Query("SELECT * FROM movie WHERE imdbID LIKE :id")
    fun getMovieById(id:String) : Movie
    }