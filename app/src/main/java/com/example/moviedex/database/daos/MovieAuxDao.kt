package com.example.moviedex.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviedex.database.entities.Movie
import com.example.moviedex.database.entities.MovieAux

@Dao
interface MovieAuxDao {

    @Query("SELECT * FROM movie_aux")
    suspend fun getAllMoviesaux() : List<MovieAux>

    @Insert
    suspend fun insertaux(movie : MovieAux?)

    @Query("DELETE FROM movie_aux")
    suspend fun eraseableaux()
}