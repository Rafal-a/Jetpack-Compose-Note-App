package com.example.notesapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NotesNavGraph(
    navigationController: NavHostController, viewModel: NoteViewModel ){

    NavHost(
        navController = navigationController,
        startDestination = Screens.NotePage.route
    ) {
        composable(Screens.NotePage.route) {
            NotePage(viewModel = viewModel,
                onAddClick={navigationController.navigate(Screens.AddNote.route)})
        }
        composable(Screens.AddNote.route) {
            AddNote(
                onSave = {
                    viewModel.insertNote(it)
                    navigationController.popBackStack()
                },
                onBack = {
                    navigationController.popBackStack()
                }
            )
        }

        }
    }