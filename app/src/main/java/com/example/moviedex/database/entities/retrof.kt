package com.example.moviedex.database.entities

import com.squareup.moshi.Json

data class retrof(
    @field:Json(name = "Response")
    var Respuesta:String,
    @field:Json(name = "Search")
    var Busqueda: List<MovieAux2>,
    @field:Json(name = "Result")
    var Resultado:String
) {
}