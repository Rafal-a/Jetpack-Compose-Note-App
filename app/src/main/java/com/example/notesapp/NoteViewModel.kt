package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.collections.emptyList
import kotlin.time.Duration.Companion.seconds

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {


    private val searchQuery = MutableStateFlow("")

    val notes: StateFlow<List<NoteEntity>> =
        searchQuery
            .flatMapLatest { query ->
                if (query.isBlank()) {
                    repository.getAllNotes()
                } else {
                    repository.searchNotes(query)
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    fun onSearchChange(query: String) {
        searchQuery.value = query
    }
    private val _selectedNote = MutableStateFlow<NoteEntity?>(null)
    val selectedNote: StateFlow<NoteEntity?> = _selectedNote


    fun getNoteById(id: Long){
        viewModelScope.launch {
            _selectedNote.value = repository.getNoteById(id)
        }
    }

    fun deleteNote(note: NoteEntity){
        viewModelScope.launch {
            repository.deleteNoteById(note)
        }
    }
    fun insertNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }
}
