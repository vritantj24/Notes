package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("select * from notes_table order by id asc")
    fun getAllNotes() : LiveData<List<Note>>
}