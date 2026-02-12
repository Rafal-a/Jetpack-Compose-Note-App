package com.example.notesapp

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val _dao : NotesDao) {


    suspend fun insertNote(note: NoteEntity) {
        _dao.insertNote(note)
    }

    suspend fun deleteNoteById(note: NoteEntity) {
        _dao.deleteNote(note)
    }

    suspend fun updateNote(note: NoteEntity) {
        _dao.updateNote(note)
    }

    fun getAllNotes(): Flow<List<NoteEntity>> {
        return _dao.getAllNotes()
    }

}