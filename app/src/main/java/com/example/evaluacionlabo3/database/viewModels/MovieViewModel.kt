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

        if(response.isSuccessful) with(response.body()?.Search){
            this?.forEach {
                this@MovieViewModel.insert(it)
                android.util.Log.d("terminado",it.toString()+" ingresada correctamente")
            }
        }else with(response){
            android.util.Log.d("error",response.toString())
            when(this.code()){
                404->{
                    android.widget.Toast.makeText(app, "pelicula no encontrada", android.widget.Toast.LENGTH_LONG).show()

                }
            }
        }
    }
    fun getMovieDetails(id:String) = viewModelScope.launch {
        val response2=repository.getMovieDetails(id).await()
        if(response2.isSuccessful) with(response2.body()){
            this@MovieViewModel.update(response2.body()!!)
        }else with(response2){
            android.util.Log.d("error",response2.toString())
            when(this.code()){
                404->{
                    android.widget.Toast.makeText(app, "pelicula no encontrada", android.widget.Toast.LENGTH_LONG).show()

                }
            }
        }
    }



}