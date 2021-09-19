package com.tengtonghann.android.quotieum.ui.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tengtonghann.android.quotieum.data.Data
import com.tengtonghann.android.quotieum.data.QuoteRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
@InternalCoroutinesApi
@ExperimentalPagingApi
class QuoteViewModel @Inject constructor(
    private val repository: QuoteRepositoryInterface) :
    ViewModel(), CoroutineScope by MainScope() {

    private val _quoteList = MutableLiveData<PagingData<Data>>()

    val quoteList: LiveData<PagingData<Data>>
        get() = _quoteList

    fun getQuotesList() {
        launch(Dispatchers.Main) {
            repository.getQuoteList().cachedIn(viewModelScope).distinctUntilChanged()
                .collectLatest {
                    _quoteList.value = it
                }
        }
    }

    fun insertFavoriteQuote(data: Data) {
        viewModelScope.launch {
            repository.insertQuote(data)
        }
    }

}