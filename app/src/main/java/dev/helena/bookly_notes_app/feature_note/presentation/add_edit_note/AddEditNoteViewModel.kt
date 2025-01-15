package dev.helena.bookly_notes_app.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.helena.bookly_notes_app.feature_note.domain.model.InvalidNoteException
import dev.helena.bookly_notes_app.feature_note.domain.model.Note
import dev.helena.bookly_notes_app.feature_note.domain.use_case.NotesUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    val notesUseCases: NotesUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Title for the book
    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter Title"
    ))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    // Author of the book
    private val _noteAuthor = mutableStateOf(NoteTextFieldState(
        hint = "Enter Author"
    ))
    val noteAuthor: State<NoteTextFieldState> = _noteAuthor

    // Category of the book
    private val _noteCategory = mutableStateOf(NoteTextFieldState(
        hint = "Enter Category"
    ))
    val noteCategory: State<NoteTextFieldState> = _noteCategory

    // Content for the book note
    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter your book note"
    ))
    val noteContent: State<NoteTextFieldState> = _noteContent

    // Rating
    private val _noteRating = mutableStateOf(0)
    val noteRating: State<Int> get() = _noteRating

    // Color of the book note
    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null



    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    notesUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )

                        _noteAuthor.value = noteAuthor.value.copy(
                            text = note.author,
                            isHintVisible = false
                        )
                        _noteCategory.value = noteCategory.value.copy(
                            text = note.category,
                            isHintVisible = false
                        )

                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color

                        _noteRating.value = note.rating
                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredAuthor -> {
                _noteAuthor.value = noteAuthor.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeAuthorFocus -> {
                _noteAuthor.value = noteAuthor.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteAuthor.value.text.isBlank()
                )
            }


            is AddEditNoteEvent.EnteredCategory -> {
                _noteCategory.value = noteCategory.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeCategoryFocus -> {
                _noteCategory.value = noteCategory.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteCategory.value.text.isBlank()
                )
            }


            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeContentFocus-> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _noteContent.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredRating -> {
                _noteRating.value = event.value
            }


            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }

            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        notesUseCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                author = noteAuthor.value.text,
                                category = noteCategory.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId,
                                rating = noteRating.value,
                                isFavorite = false
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message : String): UiEvent()
        object SaveNote : UiEvent()
    }
}