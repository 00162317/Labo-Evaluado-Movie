package com.example.moviedex.database.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.moviedex.database.daos.MovieDao
import com.example.moviedex.database.entities.Movie

class MovieRepository(private val movieDao:MovieDao){

    fun getAllMovies():LiveData<List<Movie>> = movieDao.getAllMovies()

    @WorkerThread
    suspend fun insertMovie(pelicula:Movie)=movieDao.insert((pelicula))
}