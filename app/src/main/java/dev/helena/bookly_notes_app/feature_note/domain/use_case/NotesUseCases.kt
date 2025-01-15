package dev.helena.bookly_notes_app.feature_note.domain.use_case

data class NotesUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote,
    val updateFavorite: UpdateFavorite
)
