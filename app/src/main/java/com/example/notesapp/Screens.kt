package com.example.notesapp

sealed class Screens (val route:String){
    data object NotePage: Screens("home")
    data object AddNote: Screens("AddNote")
     object  EditNote: Screens("EditNote/{noteId}"){
        fun createRoute(noteId: Long) = "EditNote/$noteId"
    }
}