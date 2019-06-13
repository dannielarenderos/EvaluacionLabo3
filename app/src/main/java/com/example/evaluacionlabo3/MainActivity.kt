package com.example.evaluacionlabo3

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.evaluacionlabo3.Fragments.MovieDetailFragment
import com.example.evaluacionlabo3.Fragments.MoviesListFragment
import com.example.evaluacionlabo3.database.entities.Movie
import com.example.evaluacionlabo3.database.viewModels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MoviesListFragment.ListenerTools {


    private var movieList = ArrayList<Movie>()
    private lateinit var listFragment: MoviesListFragment
    private lateinit var mainContentFragment: MovieDetailFragment
    private lateinit var viewModel : MovieViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMainFragment()
        bind()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("movieList", movieList)
        super.onSaveInstanceState(outState)
    }

    override fun managePortraitItemClick(item: Movie) {
        val movieBundle = Bundle()
        movieBundle.putParcelable("MOVIE",item)
        startActivity(Intent(this, MovieViewer::class.java).putExtras(movieBundle))
    }

    override fun manageLandscapeItemClick(item: Movie) {
        mainContentFragment = MovieDetailFragment.newInstance(item)
        changeFragment(R.id.land_main_content_fragment, mainContentFragment)
    }

    fun initMainFragment() {

        listFragment = MoviesListFragment.newInstance(movieList)


        val resource = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.main_fragment

        else{
            mainContentFragment = MovieDetailFragment.newInstance(Movie(
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString(),
                getText(R.string.n_a_value).toString()
            ))

            changeFragment(R.id.land_main_content_fragment, mainContentFragment)

            R.id.land_list_fragment
        }

        changeFragment(resource, listFragment)

    }

    private fun changeFragment(id: Int, frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }

    private fun bind(){
        viewModel= ViewModelProviders.of(this).get(MovieViewModel::class.java)


        viewModel.getAll().observe(this, Observer {
            listFragment.updateList(it as ArrayList<Movie>)
        })

        btn_search?.setOnClickListener {
            if(internetDisponible()){
                viewModel.retriveMovie(et_clue.text.toString())
            }

        }
    }

    fun internetDisponible(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }



}
