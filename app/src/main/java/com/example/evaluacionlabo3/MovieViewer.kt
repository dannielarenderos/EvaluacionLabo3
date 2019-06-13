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
    private lateinit var viewModel : MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_viewer)
        viewModel= ViewModelProviders.of(this).get(MovieViewModel::class.java)
        val mIntent = intent

        if(mIntent!=null){

            var receiver: Movie = intent?.extras?.getParcelable("MOVIE") ?:Movie(
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString()

            )

            var id = intent.getStringExtra("imdb")

            viewModel.getMovieById (id) {
                var item = it.value?:Movie(
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString(),
                    getText(R.string.n_a_value).toString()
                )
                Log.d("llegue", item.toString())
                bindData(item)
            }

            bindImage(receiver)
        }
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


    }
}
