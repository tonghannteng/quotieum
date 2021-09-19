package com.tengtonghann.android.quotieum.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengtonghann.android.quotieum.data.Data
import com.tengtonghann.android.quotieum.data.QuoteRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: QuoteRepositoryInterface
) : ViewModel() {

    private val _favoriteQuote = MutableLiveData<List<Data>>()

    val favoriteQuote: LiveData<List<Data>>
        get() = _favoriteQuote

    fun getFavoriteQuote() {
        viewModelScope.launch {
            repository.getFavoriteQuote().collect {
                _favoriteQuote.value = it
            }
        }
    }
}