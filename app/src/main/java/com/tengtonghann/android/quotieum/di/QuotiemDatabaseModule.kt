package com.tengtonghann.android.quotieum.di

import android.app.Application
import com.tengtonghann.android.quotieum.data.db.QuotieumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class QuotiemDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = QuotieumDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideQuoteDao(quotieumDatabase: QuotieumDatabase) = quotieumDatabase.getQuoteDao()
}