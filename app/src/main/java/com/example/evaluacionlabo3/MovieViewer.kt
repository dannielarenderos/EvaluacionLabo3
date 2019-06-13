package com.example.evaluacionlabo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.evaluacionlabo3.database.entities.Movie
import com.example.evaluacionlabo3.database.viewModels.MovieViewModel
import kotlinx.android.synthetic.main.activity_movie_viewer.*


class MovieViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_viewer)
        val reciever: Movie? = intent?.extras?.getParcelable("MOVIE")
        if(reciever!= null){
            Log.d("llegada", reciever.toString())
            init(reciever)
        }


    }

    fun init(reciever:Movie){
        bindImage(reciever)
        bindData(reciever)
    }

    fun bindImage(item: Movie){
        Glide.with(this)
            .load(item.Poster)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(app_bar_image_viewer)
    }

    fun bindData(item:Movie) {
        movie_title.text = item.Title
        description.text = item.Plot
        year.text = item.Year
        director_detalles.text = item.Director
        actores_detalles.text = item.Actors
        genre_detalles.text=item.Genre
        duracion_detalles.text=item.Runtime


    }
}
