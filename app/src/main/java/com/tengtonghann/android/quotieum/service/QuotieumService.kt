package com.tengtonghann.android.quotieum.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotieumService {

    @GET("api/v3/quotes")
    suspend fun getQuotes(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<Quotes>
}