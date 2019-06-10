package com.example.moviedex.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey(autoGenerate = true) var id : Int,
    var title : String,
    var year : String,
    var released : String,
    var runtime : String,
    var genre : String,
    var director : String,
    var actors : String,
    var plot : String,
    var language : String,
    var imdbRating : String,
    var poster : String
)