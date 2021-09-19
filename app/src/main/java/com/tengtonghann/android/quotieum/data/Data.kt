package com.tengtonghann.android.quotieum.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tengtonghann.android.quotieum.data.Data.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Data(

    val _vv: Int,
    @PrimaryKey
    val _id: String,
    val quoteAuthor: String,
    val quoteGenre: String,
    val quoteText: String
) {
    companion object {
        const val TABLE_NAME = "quote"
    }
}
// performance, improvement