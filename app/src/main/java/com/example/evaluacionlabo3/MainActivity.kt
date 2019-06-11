package com.example.evaluacionlabo3

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.evaluacionlabo3.Fragments.MovieDetailFragment
import com.example.evaluacionlabo3.Fragments.MoviesListFragment
import com.example.evaluacionlabo3.database.entities.Movie

class MainActivity : AppCompatActivity(), MoviesListFragment.ListenerTools {


    private var movieList = ArrayList<Movie>()
    private lateinit var listFragment: MoviesListFragment
    private lateinit var mainContentFragment: MovieDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMainFragment()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("movieList", movieList)
        super.onSaveInstanceState(outState)
    }

    override fun managePortraitItemClick(item: Movie) {
        val coinBundle = Bundle()

        coinBundle.putParcelable("COIN", item)
        startActivity(Intent(this, MovieViewer::class.java).putExtras(coinBundle))
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
}
