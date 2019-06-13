package com.example.moviedex.database.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.moviedex.R
import com.example.moviedex.database.entities.Movie
import kotlinx.android.synthetic.main.fragment_main_content.view.*

class MainContentFragment: Fragment() {

    var movie = Movie()

    companion object {
        fun newInstance(movie: Movie): MainContentFragment{
            val newFragment = MainContentFragment()
            newFragment.movie = movie
            return newFragment
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main_content, container, false)

        bindData(view)

        return view
    }

    fun bindData(view: View){
        view.movie_title_main_content_fragment.text = movie.Title
        view.movie_rate_main_content_fragment.text = movie.imdbRating
        view.plot_main_content_fragment.text = movie.Plot
        view.released_main_content_fragment.text = movie.Released
        view.genre_main_content_fragment.text = movie.Genre
        view.runtime_main_content_fragment.text = movie.Runtime
        view.director_main_content_fragment.text = movie.Director
        view.actors_main_content_fragment.text = movie.Actors
        Glide.with(view).load(movie.Poster)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.image_main_content_fragment)
    }

}
