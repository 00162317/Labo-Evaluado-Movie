package com.example.moviedex.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviedex.database.entities.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAllMovies() : LiveData<List<Movie>>

    @Insert
    suspend fun insert(movie : Movie)

}