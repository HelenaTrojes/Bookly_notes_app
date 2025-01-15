package dev.helena.bookly_notes_app.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.helena.bookly_notes_app.ui.theme.BabyBlue
import dev.helena.bookly_notes_app.ui.theme.LightGreen
import dev.helena.bookly_notes_app.ui.theme.RedOrange
import dev.helena.bookly_notes_app.ui.theme.RedPink
import dev.helena.bookly_notes_app.ui.theme.Violet

@Entity
data class Note (
    val title: String,
    val author: String,
    val category: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val rating: Int = 0,
    val isFavorite: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, RedPink, BabyBlue, LightGreen, Violet)
    }
}

class InvalidNoteException(message: String): Exception(message)
