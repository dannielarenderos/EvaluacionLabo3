package com.example.evaluacionlabo3.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.evaluacionlabo3.R
import com.example.evaluacionlabo3.database.entities.Movie
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*

class MovieDetailFragment : Fragment() {
    private lateinit var movie : Movie

    companion object {
        fun newInstance (dataset : Movie) : MovieDetailFragment{
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("movie", dataset)
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable("movie")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        bindData(view)

        return view
    }

    fun bindData(view: View) {

            Glide.with(view.context)
                .load(movie.Poster)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(view.image_main_content_fragment)

            view.movie_title_main_content_fragment.text = movie.Title
    }
}
