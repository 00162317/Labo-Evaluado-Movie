package com.example.moviedex.database.retrofit

import com.example.moviedex.database.entities.Movie
import com.example.moviedex.database.entities.MovieAux
import com.example.moviedex.database.entities.retrof
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val PELIS_DB = "http://www.omdbapi.com/"
interface retrofit {
    @GET("/")
    fun obtainMovies(@Query("s")clue:String, @Query("apikey")apikey:String): Deferred<Response<retrof>>

    @GET("/")
    fun obtainMovieByName(@Query("i")clue:String, @Query("apikey")apikey:String): Deferred<Response<Movie>>

    @GET("/")
    fun obtainMovieAuxByName(@Query("i")clue:String, @Query("apikey")apikey:String): Deferred<Response<MovieAux>>

    companion object{
        fun getConcidences(): retrofit{
            return Retrofit.Builder().baseUrl(PELIS_DB).addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory()).build().create(retrofit::class.java)
        }
    }
}