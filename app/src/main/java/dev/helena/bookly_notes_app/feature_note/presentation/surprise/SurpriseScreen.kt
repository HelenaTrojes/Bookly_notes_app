package dev.helena.bookly_notes_app.feature_note.presentation.surprise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.helena.bookly_notes_app.feature_note.presentation.add_edit_note.AddEditNoteViewModel
import dev.helena.bookly_notes_app.feature_note.presentation.util.Screen

@Composable
fun SurpriseScreen(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    val notes = viewModel.notesUseCases.getNotes().collectAsState(initial = emptyList()).value

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

            .padding(56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = "Feeling",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "nostalgic?",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Choose a random book from your notes to relive the feeling once more.",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(40.dp))

        if (notes.isNotEmpty()) {
            Button(onClick = {

                val randomNote = notes.random()

                navController.navigate(
                    Screen.AddEditNoteScreen.route +
                            "?noteId=${randomNote.id}&noteColor=${randomNote.color}"
                )

            }) {
                Text(text = "Go to Note")
            }
        } else {
            Text(
                text = "No notes available.",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}