package dev.helena.bookly_notes_app.feature_note.presentation.notes

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentSatisfiedAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.helena.bookly_notes_app.feature_note.presentation.notes.components.NoteItem
import dev.helena.bookly_notes_app.feature_note.presentation.notes.components.OrderSection
import dev.helena.bookly_notes_app.feature_note.presentation.util.Screen
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        backgroundColor = Color(0xFFADD8E6),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
        scaffoldState = scaffoldState
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
                .padding(top = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bookly Notes",
                    style = MaterialTheme.typography.h4,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )

                IconButton(
                    onClick = {
                        navController.navigate(Screen.FavouritesScreen.route)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favourites",
                        tint = Color.Red
                    )
                }

                IconButton(
                    onClick = {
                        viewModel.onEvent(NotesEvent.ToggleOrderSelection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort",
                        tint = Color.Black
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.notes) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditNoteScreen.route +
                                            "?noteId=${note.id}&noteColor=${note.color}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note deleted",
                                    actionLabel = "Undo"
                                )
                                if(result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NotesEvent.RestoreNote)
                                }
                            }
                        },

                        onFavoriteClick = {clickedNote ->
                            viewModel.onEvent(NotesEvent.ToggleFavorite(clickedNote))
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(
                onClick = {
                    navController.navigate(Screen.SurpriseScreen.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.SentimentSatisfiedAlt,
                    contentDescription = "Smiley Face",
                    modifier = Modifier.size(45.dp),
                    tint = Color.Black
                )
            }
        }


    }
}