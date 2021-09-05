package com.tengtonghann.android.quotieum.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tengtonghann.android.quotieum.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _quoteNavigationLiveData = MutableLiveData<Event<Boolean>>()
    private val _favoriteNavigationLiveData = MutableLiveData<Event<Boolean>>()

    val quoteNavigationLiveData: LiveData<Event<Boolean>>
        get() = _quoteNavigationLiveData

    val favoriteNavigationLiveData: LiveData<Event<Boolean>>
        get() = _favoriteNavigationLiveData

    fun onCreated() {
        _quoteNavigationLiveData.postValue(Event(true))
    }

    fun onQuoteSelected() {
        _quoteNavigationLiveData.postValue(Event(true))
    }

    fun onFavoriteSelected() {
        _favoriteNavigationLiveData.postValue(Event(true))
    }
}