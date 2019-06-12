package com.example.moviedex.database.fragments

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviedex.R
import com.example.moviedex.database.AppConstants
import com.example.moviedex.database.MyMovieAdapter
import com.example.moviedex.database.adapters.MovieAdapter
import com.example.moviedex.database.adapters.MovieSimpleListAdapter
import com.example.moviedex.database.entities.Movie
import com.example.moviedex.database.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_main_list.*
import kotlinx.android.synthetic.main.fragment_main_list.view.*

class MainListFragment: Fragment(){

    private lateinit var  movies :ArrayList<Movie>
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var moviesAdapter : MyMovieAdapter
    var listenerTool :  SearchNewMovieListener? = null

    //Habia hecho una lista para probarlo
    private val movies_prueba : ArrayList<String> = arrayListOf("Matrix", "Avengers","Toy Story 1","Toy Story 2", "Toy Story 3", "Aladdin", "Alicia en el pais de las maravillas")


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
        val view = inflater.inflate(R.layout.fragment_main_list, container, false).apply {
            //El contexto era el de la vista como tal, porque el contexto del fragmento si no tiene nada
            val adapter1: ArrayAdapter<String> = ArrayAdapter(this.context, android.R.layout.simple_list_item_1, movies_prueba)
            Log.d("Lista", movies_prueba.toString())
            movie_name_et_.setAdapter(adapter1)
        }
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        //En los videos el manejo de autocompletar lo hacian en el MainActivity pero como estamos ocupando fragmentos
        // no se si eso afectara, pero por eso lo puse en el onCreateView del fragmento

        //En el xml de este fragmento en ves de editText es AutoCompleteTextView y lo cambie a eso para que funcionara
        //Ya estan puestos los permisos para verificar si hay internet o no

        //val adapter1: ArrayAdapter<String> = ArrayAdapter(view, android.R.layout.simple_list_item_1, movies_prueba)
        //Log.d("Lista", movies_prueba.toString())
        //movie_name_et_.setAdapter(adapter1) //Esto da nulo :/

        // Creo que esto no funciona mas por el ROOM if(savedInstanceState != null) movies = savedInstanceState.getParcelableArrayList<Movie>(AppConstants.MAIN_LIST_KEY)!!

        initRecyclerView(resources.configuration.orientation, view)
        initSearchButton(view)

        return view
    }

    fun initRecyclerView(orientation:Int, container:View){
        val linearLayoutManager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            moviesAdapter = MovieAdapter({movie:Movie->listenerTool?.managePortraitItemClick(movie)})
            container.movie_list_rv.adapter = moviesAdapter as MovieAdapter

            movieViewModel.getAllPeliculas().observe(this, Observer { movies ->
                movies?.let {moviesAdapter.changeDataSet(it)}
            })
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            moviesAdapter = MovieSimpleListAdapter({movie:Movie->listenerTool?.manageLandscapeItemClick(movie)})
            container.movie_list_rv.adapter = moviesAdapter as MovieSimpleListAdapter

            movieViewModel.getAllPeliculas().observe(this, Observer { movies ->
                movies?.let {moviesAdapter.changeDataSet(it)}
            })
        }

        container.movie_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    fun initSearchButton(container:View) = container.add_movie_btn.setOnClickListener {
        listenerTool?.searchMovie(movie_name_et_.text.toString())
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

    /*override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.MAIN_LIST_KEY, movies)
        super.onSaveInstanceState(outState)
    }*/ // Esto ya no se utiliza por el room

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }
}
