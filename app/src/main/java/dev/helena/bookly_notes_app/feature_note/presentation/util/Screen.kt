package dev.helena.bookly_notes_app.feature_note.presentation.util

sealed class Screen(val route: String) {
    object NotesScreen : Screen("notes_screen")
    object AddEditNoteScreen : Screen("add_edit_note_screen")
    object WelcomeScreen : Screen("welcome_screen")
    object SurpriseScreen: Screen("surprise_screen")
    object FavouritesScreen: Screen("favourites_screen")
}