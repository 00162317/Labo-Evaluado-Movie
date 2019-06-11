package com.example.moviedex.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    var Title : String = "N/A",
    var Year : String = "N/A",
    var Released : String = "N/A",
    var Runtime : String = "N/A",
    var Genre : String = "N/A",
    var Director : String = "N/A",
    var Actors : String = "N/A",
    var Plot : String = "N/A",
    var Language : String = "N/A",
    var imdbRating : String = "N/A",
    var Poster : String = "N/A"
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}