package com.example.notesapp

import androidx.lifecycle.LiveData
import com.example.notesapp.data.NoteEntity
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

    fun getAllNotes(): Flow<List<NoteEntity>> {
        return _dao.getAllNotes()
    }

}