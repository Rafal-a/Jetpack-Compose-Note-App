package com.example.notesapp.view.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.notesapp.viewmodel.NoteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotePage(
    onNoteClick: (Long) -> Unit,
    viewModel: NoteViewModel,
    onAddClick: () -> Unit
) {
    val notes by viewModel.notes.collectAsStateWithLifecycle(emptyList())
    var isSearching by rememberSaveable { mutableStateOf(false) }
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearching) {
                        TextField(
                            value = searchQuery,
                            onValueChange = {
                                searchQuery = it
                                viewModel.onSearchChange(it)
                            },
                            placeholder = { Text("Search notes…") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    } else {
                        Text("Notes App")
                    }
                },
                navigationIcon = {
                    if (isSearching) {
                        IconButton(onClick = {
                            isSearching = false
                            searchQuery = ""
                            viewModel.onSearchChange("")
                        }) {
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "Close search"
                            )
                        }
                    }
                },
                actions = {
                    if (!isSearching) {
                        IconButton(onClick = { isSearching = true }) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { padding ->

        LazyColumn(modifier = Modifier.padding(padding)) {
            items(notes, key = { it.id }) { note ->

                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { value ->
                        if (
                            value == SwipeToDismissBoxValue.StartToEnd ||
                            value == SwipeToDismissBoxValue.EndToStart
                        ) {
                            viewModel.deleteNote(note)
                            true
                        } else false
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = {
                        DeleteBackgroundM3(dismissState.currentValue)
                    },
                    content = {
                        NoteDesign(
                            title = note.title,
                            content = note.content,
                            date = note.date,
                            onClick = { onNoteClick(note.id) }
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackgroundM3(direction: SwipeToDismissBoxValue) {
    val isSwiping = direction != SwipeToDismissBoxValue.Settled

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSwiping) Color.Red else Color.Transparent)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        contentAlignment = if (direction == SwipeToDismissBoxValue.StartToEnd)
            Alignment.CenterStart else Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = Color.Red
        )
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text("Search notes...") },
        singleLine = true,
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        }
    )
}


