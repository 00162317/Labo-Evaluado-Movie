package com.example.moviedex.database.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.moviedex.R
import com.example.moviedex.database.entities.Movie
import kotlinx.android.synthetic.main.activity_movie_viewer.*

class MovieViewerActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_viewer)

        setSupportActionBar(toolbarviewer)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(true)
        collapsingtoolbarviewer.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingtoolbarviewer.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

        val reciever: Movie = intent?.extras?.getParcelable("MOVIE") ?: Movie()
        init(reciever)
        //Esto ya ya se repite jejejeinit(Movie("","","","","","","","","","",""))
    }

    fun init(movie: Movie){
        Glide.with(this)
            .load(movie.Poster)
            .placeholder(R.drawable.ic_launcher_background)
            .into(app_bar_image_viewer)
        collapsingtoolbarviewer.title= movie.Title
        app_bar_rating_viewer.text = movie.imdbRating
        plot_viewer.text = movie.Plot
        director_viewer.text = movie.Director
        actors_viewer.text = movie.Actors
        genre_viewer.text = movie.Genre
        released_viewer.text = movie.Released
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {onBackPressed();true}
            else -> super.onOptionsItemSelected(item)
        }
    }
}
