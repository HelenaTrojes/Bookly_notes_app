package dev.helena.bookly_notes_app.feature_note.presentation.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.helena.bookly_notes_app.feature_note.presentation.notes.NotesEvent
import dev.helena.bookly_notes_app.feature_note.presentation.notes.NotesViewModel
import dev.helena.bookly_notes_app.feature_note.presentation.notes.components.NoteItem
import dev.helena.bookly_notes_app.feature_note.presentation.util.Screen

@Composable
fun FavouritesScreen(navController: NavController, viewModel: NotesViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val favouriteNotes = state.notes.filter { it.isFavorite }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6))
            .padding(20.dp)
    ) {
        IconButton(
            onClick = {navController.navigate(Screen.NotesScreen.route)},
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 30.dp, start = 7.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Favorites",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            items(favouriteNotes) { note ->
                NoteItem(
                    note = note,
                    onFavoriteClick = { clickedNote ->
                        viewModel.onEvent(NotesEvent.ToggleFavorite(clickedNote))
                    },
                    onDeleteClick = { clickedNote ->
                        viewModel.onEvent(NotesEvent.DeleteNote(clickedNote))
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}


