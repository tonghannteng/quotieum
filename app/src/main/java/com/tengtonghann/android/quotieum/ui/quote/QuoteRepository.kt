package com.tengtonghann.android.quotieum.ui.quote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tengtonghann.android.quotieum.data.*
import com.tengtonghann.android.quotieum.service.QuotieumService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class QuoteRepository @Inject constructor(private val quotieumService: QuotieumService) :
    QuoteRepositoryImpl {

    companion object {
        const val PAGE_SIZE = 5
    }

    override suspend fun getQuoteList(): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = true
            ),
            pagingSourceFactory = {
                QuotePagingSource(quotieumService)
            }
        ).flow
    }
}

interface QuoteRepositoryImpl {
    suspend fun getQuoteList(): Flow<PagingData<Data>>
}