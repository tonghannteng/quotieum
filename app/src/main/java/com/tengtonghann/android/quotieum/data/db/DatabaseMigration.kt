package com.tengtonghann.android.quotieum.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tengtonghann.android.quotieum.data.Data

/**
 * Database Migration
 */
object DatabaseMigration {

    const val DB_VERSION = 1

    val MIGRATION: Array<Migration>
        get() = arrayOf(migration1())

    private fun migration1(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Data.TABLE_NAME} ADD COLUMN body TEXT")
        }

    }
}