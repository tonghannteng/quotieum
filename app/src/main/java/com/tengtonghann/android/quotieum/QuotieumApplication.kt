package com.tengtonghann.android.quotieum

import android.app.Application
import com.facebook.stetho.Stetho
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
        Stetho.initializeWithDefaults(this)
    }
}