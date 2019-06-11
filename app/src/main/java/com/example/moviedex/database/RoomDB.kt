package com.example.moviedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviedex.database.daos.MovieDao
import com.example.moviedex.database.entities.Movie

@Database(entities = [Movie::class],version = 1,exportSchema = false)
abstract class RoomDB:RoomDatabase() {

    abstract fun movieDao():MovieDao

    companion object {
        @Volatile
        private var INSTANCE:RoomDB?=null

        fun getDatabase(
            context: Context
        ):RoomDB{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room
                    .databaseBuilder(context,RoomDB::class.java,"movie_table")
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }
}