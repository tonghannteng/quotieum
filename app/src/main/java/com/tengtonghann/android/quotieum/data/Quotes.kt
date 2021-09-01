package com.tengtonghann.android.quotieum.data

data class Quotes(
    val data: List<Data>,
    val message: String,
    val pagination: Pagination,
    val statusCode: Int,
    val totalQuote: Int
)