package com.example.notesapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao{

    @Insert
    suspend fun  insertNote(note: Note)
    @Delete
    suspend fun deleteNoteById(note: Note)
    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM Notes")
    fun getAllNotes(): Flow<List<Note>> // Flow for getting all the notes without refresh :D
}