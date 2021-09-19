package com.tengtonghann.android.quotieum.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tengtonghann.android.quotieum.data.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteQuote(quote: Data)

    @Query("SELECT * FROM ${Data.TABLE_NAME}")
    fun getAllFavoriteQuote(): Flow<List<Data>>
}