package com.example.notesapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotePage(
    onNoteClick: (Long) -> Unit,
    viewModel: NoteViewModel,
    onAddClick: () -> Unit
) {
    val notes by viewModel.notes.collectAsStateWithLifecycle(initialValue = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { padding ->

        LazyColumn(modifier = Modifier.padding(padding)) {
            items(
                items = notes,
                key = { note -> note.id }
            ) { note ->

                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { value ->
                        if (value == SwipeToDismissBoxValue.StartToEnd ||
                            value == SwipeToDismissBoxValue.EndToStart
                        ) {
                            viewModel.deleteNote(note)
                            true
                        } else {
                            false
                        }
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = {
                        DeleteBackgroundM3(dismissState.dismissDirection)
                    },
                    content = {
                        NoteDesign(
                            title = note.title,
                            content = note.content,
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
            .background(if (isSwiping) Color.Gray else Color.Transparent)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        contentAlignment = if (direction == SwipeToDismissBoxValue.StartToEnd)
            Alignment.CenterStart else Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = Color.White
        )
    }
}



