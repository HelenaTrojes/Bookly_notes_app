package dev.helena.bookly_notes_app.feature_note.domain.use_case

import dev.helena.bookly_notes_app.feature_note.domain.model.Note
import dev.helena.bookly_notes_app.feature_note.domain.repository.NoteRepository

class UpdateFavorite(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.updateFavorite(note)
    }
}