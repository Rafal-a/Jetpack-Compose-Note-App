package com.example.notesapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NotePage(padding: PaddingValues,viewModel: NoteViewModel ) {

    val notes by viewModel.notes.collectAsStateWithLifecycle(emptyList())
    LazyColumn(modifier = Modifier.padding(padding)) {
        items(notes){ it->
            NoteDesign(title = it.title, content = it.content)
        }

    }

}
