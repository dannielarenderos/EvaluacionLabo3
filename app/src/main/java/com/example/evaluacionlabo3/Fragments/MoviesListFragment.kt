package com.example.evaluacionlabo3.Fragments


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evaluacionlabo3.Adapter.MovieAdapter

import com.example.evaluacionlabo3.R
import com.example.evaluacionlabo3.database.entities.Movie
import kotlinx.android.synthetic.main.fragment_movies_list.view.*

class MoviesListFragment : Fragment() {

    private lateinit var movieList :ArrayList<Movie>
    private lateinit var movieAdapter : MovieAdapter
    var listenerTools :  ListenerTools? = null

    interface ListenerTools{
        fun managePortraitItemClick(item: Movie)

        fun manageLandscapeItemClick(item: Movie)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<Movie>) =
            MoviesListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("list", param1)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieList = it.getParcelableArrayList("list")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        initRecyclerView(resources.configuration.orientation, view)
        return view
    }

    private fun initRecyclerView(orientation:Int, container: View){
        val linearLayoutManager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            movieAdapter =
                MovieAdapter(
                    movieList
                ) { item: Movie -> listenerTools?.managePortraitItemClick(item) }

            container.recyclerView.adapter = movieAdapter

            container.recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
            }
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            movieAdapter =
                MovieAdapter(
                    movieList
                ) { item: Movie -> listenerTools?.manageLandscapeItemClick(item) }

            container.recyclerView.adapter = movieAdapter

            container.recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
            }
        }

    }

    fun updateBookList(bookList: ArrayList<Movie>){ movieAdapter.changeDataSet(bookList) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListenerTools) {
            listenerTools = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("movieList", movieList)
        super.onSaveInstanceState(outState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerTools = null
    }
}