package com.example.moviedex.database.entities

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(tableName = "movie_aux")
data class MovieAux2(
    var Title : String = "N/A",
    var Year : String = "N/A",
    var Released : String = "N/A",
    var Runtime : String = "N/A",
    var Genre : String = "N/A",
    @field:Json(name = "Director")
    var Director : String = "N/A",
    @field:Json(name = "Actors")
    var Actors : String = "N/A",
    var Plot : String = "N/A",
    @field:Json(name = "imdbID")
    var imdbID : String = "N/A",
    var Language : String = "N/A",
    var imdbRating : String = "N/A",
    var Poster : String = "N/A"
)