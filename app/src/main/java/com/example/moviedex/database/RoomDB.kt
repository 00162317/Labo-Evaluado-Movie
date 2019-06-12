package com.example.moviedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moviedex.database.daos.MovieDao
import com.example.moviedex.database.entities.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Movie::class],version = 1,exportSchema = false)
abstract class RoomDB:RoomDatabase() {

    abstract fun movieDao():MovieDao

    companion object {
        @Volatile
        private var INSTANCE:RoomDB?=null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ):RoomDB{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room
                    .databaseBuilder(context,RoomDB::class.java,"movie_table")
                    .fallbackToDestructiveMigration() //por si matan la db, este metodo sirve solo es de ponerle un numero mas alto a la version
                    .addCallback(MovieDatabaseCallback(scope))
                    .build()
                INSTANCE=instance
                return instance
            }
        }

        //Callback solo para hacer pruebas
        private class MovieDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.movieDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(movieDao: MovieDao) {
            // Popular la base de manera manual para pruebas

            var movie1 = Movie("Avengers","", "", "", "", "", ""
            ," ", " ", "", "")
            movieDao.insert(movie1)

        }
    }
}