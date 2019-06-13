package com.example.moviedex.database.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.moviedex.database.daos.MovieAuxDao
import com.example.moviedex.database.daos.MovieDao
import com.example.moviedex.database.entities.Movie
import com.example.moviedex.database.entities.MovieAux
import com.example.moviedex.database.entities.retrof
import com.example.moviedex.database.retrofit.retrofit
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepository(private val movieDao:MovieDao, private val movieAuxDao: MovieAuxDao){
    fun retrieverepoAsync(eje:String) : Deferred<Response<retrof>> {
        //val apiK = "ffb96d82"
        val apiK = "55b38c32"
        return retrofit.getConcidences().obtainMovies(eje,apiK)
    }

    fun retrieverepoOneMovie(eje:String) : Deferred<Response<Movie>> {
        val apiK = "55b38c32"
        return retrofit.getConcidences().obtainMovieByName(eje,apiK)
    }

    fun retrieverepoOneAuxMovie(eje:String) : Deferred<Response<MovieAux>> {
        val apiK = "55b38c32"
        return retrofit.getConcidences().obtainMovieAuxByName(eje,apiK)
    }

    fun getAllMovies():LiveData<List<Movie>> = movieDao.getAllMovies()

    @WorkerThread
    suspend fun insertMovie(pelicula:Movie?)=movieDao.insert((pelicula))

    @WorkerThread
    suspend fun insertMovieAux(pelicula: MovieAux?)=movieAuxDao.insertaux((pelicula))

    @WorkerThread
    suspend fun nuke()=movieAuxDao.eraseableaux()
}