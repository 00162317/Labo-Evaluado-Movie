package com.example.moviedex.database.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedex.R
import com.example.moviedex.database.MyMovieAdapter
import com.example.moviedex.database.entities.Movie
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieSimpleListAdapter(var movies:List<Movie>, val clickListener: (Movie) -> Unit): RecyclerView.Adapter<MovieSimpleListAdapter.ViewHolder>(),
    MyMovieAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) =holder.bind(movies[pos], clickListener)

    override fun changeDataSet(newDataSet: List<Movie>) {
        this.movies = newDataSet
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, clickListener: (Movie) -> Unit) = with(itemView){
            title_list_item.text = movie.Title
            genre_list_item.text = movie.Genre
            runtime_list_item.text = movie.Runtime
            this.setOnClickListener { clickListener(movie) }
        }
    }
}