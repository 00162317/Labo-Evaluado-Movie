package com.example.moviedex.database.fragments

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviedex.R
import com.example.moviedex.database.AppConstants
import com.example.moviedex.database.MyMovieAdapter
import com.example.moviedex.database.adapters.MovieAdapter
import com.example.moviedex.database.adapters.MovieSimpleListAdapter
import com.example.moviedex.database.entities.Movie
import kotlinx.android.synthetic.main.fragment_main_list.*
import kotlinx.android.synthetic.main.fragment_main_list.view.*

class MainListFragment: Fragment(){

    private lateinit var  movies :ArrayList<Movie>
    private lateinit var moviesAdapter : MyMovieAdapter
    var listenerTool :  SearchNewMovieListener? = null

    companion object {
        fun newInstance(dataset : ArrayList<Movie>): MainListFragment{
            val newFragment = MainListFragment()
            newFragment.movies = dataset
            return newFragment
        }
    }

    interface SearchNewMovieListener{
        fun searchMovie(movieName: String)

        fun managePortraitItemClick(movie: Movie)

        fun manageLandscapeItemClick(movie: Movie)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main_list, container, false)

        if(savedInstanceState != null) movies = savedInstanceState.getParcelableArrayList<Movie>(AppConstants.MAIN_LIST_KEY)!!

        initRecyclerView(resources.configuration.orientation, view)
        initSearchButton(view)

        return view
    }

    fun initRecyclerView(orientation:Int, container:View){
        val linearLayoutManager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            moviesAdapter = MovieAdapter(movies, {movie:Movie->listenerTool?.managePortraitItemClick(movie)})
            container.movie_list_rv.adapter = moviesAdapter as MovieAdapter
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            moviesAdapter = MovieSimpleListAdapter(movies, {movie:Movie->listenerTool?.manageLandscapeItemClick(movie)})
            container.movie_list_rv.adapter = moviesAdapter as MovieSimpleListAdapter
        }

        container.movie_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    fun initSearchButton(container:View) = container.add_movie_btn.setOnClickListener {
        listenerTool?.searchMovie(movie_name_et.text.toString())
    }

    fun updateMoviesAdapter(movieList: ArrayList<Movie>){ moviesAdapter.changeDataSet(movieList) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchNewMovieListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.MAIN_LIST_KEY, movies)
        super.onSaveInstanceState(outState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }
}
