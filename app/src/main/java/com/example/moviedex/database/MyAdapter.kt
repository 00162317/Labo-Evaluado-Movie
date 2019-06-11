package com.example.moviedex.database

object AppConstants{
    val dataset_saveinstance_key = "CLE"
    val MAIN_LIST_KEY = "key_list_movies"
}

interface MyMovieAdapter {
    fun changeDataSet(newDataSet : List<Movie>)
}