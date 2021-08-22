package com.tengtonghann.android.quotieum

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Tonghann Teng
 * 08/20/2021
 */

@HiltAndroidApp
@ExperimentalCoroutinesApi
class QuotieumApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}