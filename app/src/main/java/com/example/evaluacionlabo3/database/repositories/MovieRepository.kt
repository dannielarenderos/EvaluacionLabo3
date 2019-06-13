package com.example.evaluacionlabo3.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.evaluacionlabo3.database.dao.MoviesDao
import com.example.evaluacionlabo3.database.entities.Movie
import com.example.evaluacionlabo3.retrofit.CoincidenciaService
import com.example.evaluacionlabo3.retrofit.Coincidencias
import com.example.evaluacionlabo3.retrofit.MovieDetails
import kotlinx.coroutines.Deferred
import retrofit2.Response

const val apikey = "d6c84f8c"
class MovieRepository(private val movieRepoDao : MoviesDao) {
    fun retrieveRepoAsync(clue:String) : Deferred<Response<Coincidencias>> {

        return CoincidenciaService.getCoincidenciaService().getMovies(clue,apikey)
    }


    fun getMovieDetails(id:String) : Deferred<Response<Movie>>{
        return CoincidenciaService.getCoincidenciaService().getMoviesDetails(id, apikey)
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
    @WorkerThread
    suspend fun update(movie:Movie){
        return movieRepoDao.update(movie)
    }


    fun getMovieById(id:String) : Movie{
        return movieRepoDao.getMovieById(id)
    }
}