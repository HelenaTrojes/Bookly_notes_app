package dev.helena.bookly_notes_app.feature_note.presentation.notes

import dev.helena.bookly_notes_app.feature_note.domain.model.Note
import dev.helena.bookly_notes_app.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSelection: NotesEvent()
    data class ToggleFavorite(val note: Note) : NotesEvent()
}