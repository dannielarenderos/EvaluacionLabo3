package com.example.evaluacionlabo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.evaluacionlabo3.database.entities.Movie

class MovieViewer : AppCompatActivity() {

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
}
