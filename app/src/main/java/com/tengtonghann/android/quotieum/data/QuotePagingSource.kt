package com.tengtonghann.android.quotieum.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tengtonghann.android.quotieum.service.QuotieumService

class QuotePagingSource(private val quotieumService: QuotieumService) : PagingSource<Int, Data>() {

    companion object {
        const val LIMIT_PAGING = 5
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = quotieumService.getQuotes(currentLoadingPageKey, LIMIT_PAGING)
            val responseData = mutableListOf<Data>()
            val data = response.body()?.data ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}