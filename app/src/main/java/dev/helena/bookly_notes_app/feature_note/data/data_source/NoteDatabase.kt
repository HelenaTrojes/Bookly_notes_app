package dev.helena.bookly_notes_app.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.helena.bookly_notes_app.feature_note.domain.model.Note

@Database(
    entities = [ Note :: class],
    version = 4
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes"
    }



}