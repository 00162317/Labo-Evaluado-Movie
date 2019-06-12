package com.example.moviedex.database.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedex.database.RoomDB
import com.example.moviedex.database.entities.Movie
import com.example.moviedex.database.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application):AndroidViewModel(application){

    private var repoMovie:MovieRepository?=null

    //var allMovies:LiveData<List<Movie>>?=null

    init {
        loadMovie()
    }
    //TODO: Insertar
    fun insert(peli:Movie)=viewModelScope.launch(Dispatchers.IO){
        repoMovie!!.insertMovie(peli)
    }
    //TODO: Mostrar
    fun getAllPeliculas():LiveData<List<Movie>> = repoMovie!!.getAllMovies()

    //TODO: Instancia
    private fun loadMovie(){
        val peliDao = RoomDB.getDatabase(getApplication(), viewModelScope).movieDao()

        repoMovie = MovieRepository(peliDao)
    }
}