package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.data.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao{

    @Insert
    suspend fun  insertNote(note: NoteEntity)
    @Delete
    suspend fun deleteNote(note: NoteEntity)
    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("SELECT * FROM ${Constants.TABLE_NAME} ORDER BY id=:id DESC")
    suspend fun getNoteById(id: Long): NoteEntity?

    @Query("SELECT * FROM ${Constants.TABLE_NAME} ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NoteEntity>> // Flow for getting all the notes without refresh :D
}