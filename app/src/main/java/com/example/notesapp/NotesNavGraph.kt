package com.example.notesapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NotesNavGraph(
    navigationController: NavHostController,
    viewModel: NoteViewModel
) {
    NavHost(
        navController = navigationController,
        startDestination = Screens.NotePage.route
    ) {

        // Notes list
        composable(Screens.NotePage.route) {
            NotePage(
                viewModel = viewModel,
                onAddClick = {
                    navigationController.navigate(Screens.AddNote.route)
                },
                onNoteClick = { noteId ->
                    navigationController.navigate(
                        Screens.EditNote.createRoute(noteId)
                    )
                }
            )
        }

        // ADD NOTE (no initial note)
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

        // EDIT NOTE (using id and same ui as add note)
        composable(
            route = Screens.EditNote.route,
            arguments = listOf(
                navArgument("noteId") { type = NavType.LongType }
            )
        ) { backStackEntry ->

            val noteId =
                backStackEntry.arguments?.getLong("noteId")
                    ?: return@composable

            LaunchedEffect(noteId) {
                viewModel.getNoteById(noteId)
            }

            AddNote(
                initialNote = viewModel.selectedNote.collectAsState().value,
                onSave = {
                    viewModel.updateNote(it)
                    navigationController.popBackStack()
                },
                onBack = {
                    navigationController.popBackStack()
                }
            )
        }
    }
}

