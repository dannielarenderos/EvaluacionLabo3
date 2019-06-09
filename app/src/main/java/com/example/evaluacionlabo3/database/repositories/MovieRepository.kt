package com.example.evaluacionlabo3.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.evaluacionlabo3.database.dao.MoviesDao
import com.example.evaluacionlabo3.database.entities.Movie
import com.example.evaluacionlabo3.retrofit.CoincidenciaService
import com.example.evaluacionlabo3.retrofit.Coincidencias
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepository(private val movieRepoDao : MoviesDao) {
    fun retrieveRepoAsync(clue:String) : Deferred<Response<Coincidencias>> {
        val apikey = "d6c84f8c"
        return CoincidenciaService.getCoincidenciaService().getMovies(clue,apikey)
    }

    @WorkerThread
    suspend fun insert(movie: Movie){
        movieRepoDao.insert(movie)
    }

    fun getAll(): LiveData<List<Movie>> {
        return movieRepoDao.getAllMovies()
    }

    @WorkerThread
    suspend fun nuke(){
        return movieRepoDao.nukeTable()
    }
}