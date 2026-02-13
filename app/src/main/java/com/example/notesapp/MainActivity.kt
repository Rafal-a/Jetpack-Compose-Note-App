package com.example.notesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.data.local.NotesDatabase
import com.example.notesapp.data.repository.NoteRepository
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.view.navigation.NotesNavGraph
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.notesapp.viewmodel.NoteViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    private val viewModel: NoteViewModel by viewModels {
        NoteViewModelFactory(
            NoteRepository(
                NotesDatabase
                    .getInstance(applicationContext)
                    .notesDao()
            )
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                val navController = rememberNavController()
                NotesNavGraph(
                    viewModel = viewModel,
                    navigationController = navController
                )
            }
        }
    }
}
