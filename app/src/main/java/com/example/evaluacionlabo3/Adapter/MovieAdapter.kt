package com.example.evaluacionlabo3.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.evaluacionlabo3.R
import com.example.evaluacionlabo3.database.entities.Movie
import kotlinx.android.synthetic.main.cardview_movie.view.*

class MovieAdapter(var items: ArrayList<Movie>, val clickListener: (Movie)->Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_movie,parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)

    fun changeDataSet(newDataSet: ArrayList<Movie>) {
        this.items = newDataSet
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: Movie, clickListener:  (Movie) -> Unit) = with(itemView){
            Glide.with(itemView.context)
                .load(item.Poster)
                .placeholder(R.drawable.ic_launcher_background)
                .into(movie_image_cv)

            movie_title_cv.text = item.Title
            movie_plot_cv.text = item.Rated
            movie_rate_cv.text = item.Type
            movie_runtime_cv.text = item.Year

            itemView.setOnClickListener{(clickListener(item))}
        }
    }
}