package com.example.moviedex.database.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedex.database.RoomDB
import com.example.moviedex.database.entities.Movie
import com.example.moviedex.database.entities.MovieAux
import com.example.moviedex.database.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application):AndroidViewModel(application){

    private var repoMovie:MovieRepository?=null

    //var allMovies:LiveData<List<Movie>>?=null

    init {
        loadMovie()
    }

    fun retrieveMovie(search: String) = viewModelScope.launch {
        val response = repoMovie!!.retrieverepoAsync(search).await()
        if(response.isSuccessful) with(response){
            this.body()?.let {
                this@MovieViewModel.nuke()
                it.Busqueda.forEach {
                    retrieveOneMovie(it.imdbID)
                    //this@MovieViewModel.insert(it)
                }
            }
        } else with(response){
            when(this.code()){
                404-> {
                    Toast.makeText(getApplication(), "Murio", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun retrieveOneMovie(search: String) = viewModelScope.launch {
        val response = repoMovie!!.retrieverepoOneMovie(search).await()
        if(response.isSuccessful) with(response){
            retrieveOneAuxMovie(search)
            this@MovieViewModel.insert(this.body())
        } else with(response){
            when(this.code()){
                404-> {
                    Toast.makeText(getApplication(), "Murio", Toast.LENGTH_SHORT).show()
                }
                else ->{
                    Toast.makeText(getApplication(), "Murio x2", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun retrieveOneAuxMovie(search: String) = viewModelScope.launch {
        val response = repoMovie!!.retrieverepoOneAuxMovie(search).await()
        if(response.isSuccessful) with(response){
            this@MovieViewModel.insertAux(this.body())
        } else with(response){
            when(this.code()){
                404-> {
                    Toast.makeText(getApplication(), "Murio", Toast.LENGTH_SHORT).show()
                }
                else ->{
                    Toast.makeText(getApplication(), "Murio x2", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //TODO: Insertar
    fun insert(peli:Movie?)=viewModelScope.launch(Dispatchers.IO){
        repoMovie!!.insertMovie(peli)
    }

    fun insertAux(peli: MovieAux?)=viewModelScope.launch {
        repoMovie!!.insertMovieAux(peli)
    }

    fun nuke()=viewModelScope.launch {
        repoMovie!!.nuke()
    }

    //TODO: Mostrar
    fun getAllPeliculas():LiveData<List<Movie>> = repoMovie!!.getAllMovies()

    //TODO: Instancia
    private fun loadMovie(){
        val peliDao = RoomDB.getDatabase(getApplication(), viewModelScope).movieDao()
        val peliauxDao = RoomDB.getDatabase(getApplication(), viewModelScope).movieauxDao()

        repoMovie = MovieRepository(peliDao, peliauxDao)
    }
}