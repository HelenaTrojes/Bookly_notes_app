package dev.helena.bookly_notes_app.feature_note.data.data_source

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object NoteDatabaseMigration {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Note ADD COLUMN author TEXT NOT NULL DEFAULT ''")
            database.execSQL("ALTER TABLE Note ADD COLUMN category TEXT NOT NULL DEFAULT ''")
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Migrate from version 2 to 3 (adding rating)
            database.execSQL("ALTER TABLE Note ADD COLUMN rating INTEGER NOT NULL DEFAULT 0")
        }
    }

    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Migrate from version 3 to 4 (adding favourite)
            database.execSQL("ALTER TABLE note ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")        }
    }


}