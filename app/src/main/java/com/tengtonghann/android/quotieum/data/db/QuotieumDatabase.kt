package com.tengtonghann.android.quotieum.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tengtonghann.android.quotieum.data.Data
import com.tengtonghann.android.quotieum.data.dao.QuoteDao

@Database(
    entities = [Data::class],
    version = DatabaseMigration.DB_VERSION
)
abstract class QuotieumDatabase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao

    companion object {
        private const val DB_NAME = "quote_database"

        @Volatile
        private var INSTANCE: QuotieumDatabase? = null

        fun getInstance(context: Context): QuotieumDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuotieumDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigration.MIGRATION)
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}