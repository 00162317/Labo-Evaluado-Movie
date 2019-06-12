package com.example.moviedex.database.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.moviedex.database.daos.MovieDao
import com.example.moviedex.database.entities.Movie
import com.example.moviedex.database.entities.retrof
import com.example.moviedex.database.retrofit.retrofit
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepository(private val movieDao:MovieDao){
    fun retrieverepoAsync(eje:String) : Deferred<Response<retrof>> {
        val apiK = "ffb96d82"
        return retrofit.getConcidences().obtainMovies(eje,apiK)
    }
    fun getAllMovies():LiveData<List<Movie>> = movieDao.getAllMovies()

    @WorkerThread
    suspend fun insertMovie(pelicula:Movie)=movieDao.insert((pelicula))
}