package dev.helena.bookly_notes_app.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {

    // Title
    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState : FocusState) : AddEditNoteEvent()

    // Author
    data class EnteredAuthor(val value: String) : AddEditNoteEvent()
    data class ChangeAuthorFocus(val focusState : FocusState) : AddEditNoteEvent()

    // Category
    data class EnteredCategory(val value: String) : AddEditNoteEvent()
    data class ChangeCategoryFocus(val focusState : FocusState) : AddEditNoteEvent()

    // Rating
    data class EnteredRating(val value: Int) : AddEditNoteEvent()

    // Content
    data class EnteredContent(val value: String) :  AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent()

    data class ChangeColor(val color: Int)  : AddEditNoteEvent()
    object SaveNote: AddEditNoteEvent()
}

