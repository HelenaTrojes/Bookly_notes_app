package dev.helena.bookly_notes_app.dependencyInjection

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.helena.bookly_notes_app.feature_note.data.data_source.NoteDatabase
import dev.helena.bookly_notes_app.feature_note.data.data_source.NoteDatabaseMigration
import dev.helena.bookly_notes_app.feature_note.data.repository.NoteRepositoryImplementation
import dev.helena.bookly_notes_app.feature_note.domain.repository.NoteRepository
import dev.helena.bookly_notes_app.feature_note.domain.use_case.AddNote
import dev.helena.bookly_notes_app.feature_note.domain.use_case.DeleteNote
import dev.helena.bookly_notes_app.feature_note.domain.use_case.GetNote
import dev.helena.bookly_notes_app.feature_note.domain.use_case.GetNotes
import dev.helena.bookly_notes_app.feature_note.domain.use_case.NotesUseCases
import dev.helena.bookly_notes_app.feature_note.domain.use_case.UpdateFavorite
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        )
            .addMigrations(
                NoteDatabaseMigration.MIGRATION_1_2,
                NoteDatabaseMigration.MIGRATION_2_3,
                NoteDatabaseMigration.MIGRATION_3_4
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImplementation(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NotesUseCases{
        return NotesUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository),
            updateFavorite = UpdateFavorite(repository)
        )
    }
}