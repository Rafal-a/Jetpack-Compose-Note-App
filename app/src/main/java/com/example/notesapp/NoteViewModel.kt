package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NoteRepository
    val notes: Flow<List<Note>>

    init {
        val database= NotesDatabase.getInstance(application)
        val dao = database.notesDao()
        repository = NoteRepository(dao)
        notes = repository.getAllNotes()
    }

    suspend fun insertNote(note: Note) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }
    suspend fun deleteNoteById(note: Note) {
        viewModelScope.launch {
            repository.deleteNoteById(note)
        }
    }
    suspend fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

}


