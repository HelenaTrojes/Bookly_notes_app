package dev.helena.bookly_notes_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.helena.bookly_notes_app.feature_note.presentation.add_edit_note.AddEditNoteScreen
import dev.helena.bookly_notes_app.feature_note.presentation.favourites.FavouritesScreen
import dev.helena.bookly_notes_app.feature_note.presentation.notes.NotesScreen
import dev.helena.bookly_notes_app.feature_note.presentation.surprise.SurpriseScreen
import dev.helena.bookly_notes_app.feature_note.presentation.util.Screen
import dev.helena.bookly_notes_app.feature_note.presentation.welcome.WelcomeScreen
import dev.helena.bookly_notes_app.ui.theme.Bookly_notes_appTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bookly_notes_appTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.WelcomeScreen.route
                    ) {
                        composable(route = Screen.WelcomeScreen.route) {
                            WelcomeScreen(navController = navController)
                        }
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = color
                            )
                        }

                        composable(route = Screen.SurpriseScreen.route) {
                            SurpriseScreen(navController = navController)
                        }

                        composable(route = Screen.FavouritesScreen.route) {
                            FavouritesScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
