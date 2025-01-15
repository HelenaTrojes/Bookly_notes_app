package dev.helena.bookly_notes_app.feature_note.domain.use_case

import dev.helena.bookly_notes_app.feature_note.domain.model.InvalidNoteException
import dev.helena.bookly_notes_app.feature_note.domain.model.Note
import dev.helena.bookly_notes_app.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke (note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the book cannot be empty.")
        }
        if (note.author.isBlank()) {
            throw InvalidNoteException("The author of the book cannot be empty.")
        }
        if (note.category.isBlank()) {
            throw InvalidNoteException("The category of the book cannot be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the book cannot be empty.")
        }
        repository.insertNote(note)
    }

}