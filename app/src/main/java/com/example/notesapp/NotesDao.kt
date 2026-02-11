package com.example.notesapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao{
    @Query("SELECT * FROM Notes")
    fun getAllNotes(): List<Note>
    @Query("SELECT * FROM Notes WHERE id = :id")
    fun getNoteById(id: Int): Note
    @Insert
    fun insertNote(note: Note)
    @Delete
    fun deleteNoteById(id: Int)
    @Update
    fun updateNote(id: Int, title: String, content: String)

}