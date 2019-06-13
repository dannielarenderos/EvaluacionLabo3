package com.example.evaluacionlabo3.database.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.evaluacionlabo3.database.RoomDB
import com.example.evaluacionlabo3.database.entities.Movie
import com.example.evaluacionlabo3.database.repositories.MovieRepository
import com.example.evaluacionlabo3.retrofit.MovieDetails
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val app : Application) : AndroidViewModel(app) {
    private val repository : MovieRepository
    lateinit var movie: LiveData<Movie>


    init {
        val movieDao = RoomDB.getInstance(app).movieDao()
        repository = MovieRepository(movieDao)
    }

    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(movie)
    }

    fun getAll(): LiveData<List<Movie>> {
        return repository.getAll()
    }

    private suspend fun nuke()=repository.nuke()

    fun update(movie:Movie) = viewModelScope.launch (Dispatchers.IO){
        repository.update(movie)
    }

    fun getMovieById(id:String, callback: (data: LiveData<Movie>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            movie = MutableLiveData(repository.getMovieById(id))
            callback(movie)
        }
    }


    fun retriveMovie(clue : String) = viewModelScope.launch {
        this@MovieViewModel.nuke()
        val response =  repository.retrieveRepoAsync(clue).await()

        if(response.isSuccessful)with(response.body()?.Search){
            this?.forEach {
                getMovies(it.imdbID)
            }
        }else with(response){
            when(this.code()){
                404->{

                }
            }
        }
    }
    fun getMovies (id: String) = viewModelScope.launch {
        val response = repository.getMovieDetails(id).await()
        if(response.isSuccessful)with(response.body()){
            this@MovieViewModel.insert(this!!)
        }
    }



}