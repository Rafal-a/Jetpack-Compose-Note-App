package com.example.notesapp

import kotlinx.coroutines.flow.Flow

class NoteRepository(private val _dao : NotesDao) {


    suspend fun insertNote(note: Note) {
        _dao.insertNote(note)
    }

    suspend fun deleteNoteById(note: Note) {
        _dao.deleteNoteById(note)
    }

    suspend fun updateNote(note: Note) {
        _dao.updateNote(note)
    }

    fun getAllNotes(): Flow<List<Note>> {
        return _dao.getAllNotes()
    }

}