package com.example.notesapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NotePage(padding: PaddingValues) {

    LazyColumn(modifier = Modifier.padding(padding)) {
        item {
            NoteList()
        }
    }

}
