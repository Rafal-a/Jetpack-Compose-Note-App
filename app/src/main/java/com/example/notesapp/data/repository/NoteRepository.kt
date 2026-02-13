package com.example.notesapp.data.repository

import com.example.notesapp.data.local.NotesDao
import com.example.notesapp.data.model.NoteEntity
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

    suspend fun getNoteById(id: Long): NoteEntity? {
        return _dao.getNoteById(id)
    }
    suspend fun searchNotes(query: String): Flow<List<NoteEntity>> {
        return _dao.searchNotes(query)
    }
    fun getAllNotes(): Flow<List<NoteEntity>> {
        return _dao.getAllNotes()
    }

}