package com.example.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Note::class], version = Constants.TABLE_VERSION)
@TypeConverters(Converter::class) // bring converters from Converter class

abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    // ensure one database only
    companion object {

        @Volatile // force all threads to update and take latest value
        private var instance: NotesDatabase? = null

        // synchronized makes sure to have only one instance of the database and if it doesn't exist then build  a new one
        private fun getInstance(context: Context): NotesDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    Constants.TABLE_NAME
                ).build()
                    .also { instance = it  } // make sure to update the instance value


            }
        }
    }
}
