package com.example.evaluacionlabo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatViewInflater
import com.bumptech.glide.Glide
import com.example.evaluacionlabo3.database.entities.Movie
import kotlinx.android.synthetic.main.activity_movie_viewer.*
import kotlinx.android.synthetic.main.activity_movie_viewer.view.*
import kotlinx.android.synthetic.main.cardview_movie.*

class MovieViewer : AppCompatActivity() {
    private lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_viewer)

        val mIntent = intent

        if(mIntent!=null){

            var receiver: Movie = intent?.extras?.getParcelable("MOVIE") ?:Movie(
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString()
            )

            Log.d("Movie: ", "Recibiendo: $receiver")

        }
    }

    /*fun bindData(view: View) {
        view.name_main_content_fragment.text = movie.Title
        view.value_main_content_fragment.text = movie.Type
        view.year_main_content_fragment.text = movie.Year
    }*/
}
