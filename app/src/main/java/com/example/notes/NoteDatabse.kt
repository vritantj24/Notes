package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabse : RoomDatabase() {

    abstract fun getNoteDAO():NoteDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NoteDatabse? = null

        fun getDataBase(context: Context): NoteDatabse {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabse::class.java,
                    "notes_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}