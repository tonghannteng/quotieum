package com.tengtonghann.android.quotieum.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.tengtonghann.android.quotieum.data.QuoteRepository
import com.tengtonghann.android.quotieum.data.QuoteRepositoryInterface
import com.tengtonghann.android.quotieum.data.dao.QuoteDao
import com.tengtonghann.android.quotieum.service.QuotieumService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
@Module
class QuotieumApiModule {

    @Singleton
    @Provides
    fun provideRetrofitSingleton(): QuotieumService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuotieumService::class.java)

    @Singleton
    @Provides
    fun injectRepository(
        quotieumService: QuotieumService,
        quoteDao: QuoteDao
    ) = QuoteRepository(quotieumService, quoteDao) as QuoteRepositoryInterface

    companion object {
        const val NETWORK_CALL_TIMEOUT = 60
        const val BASE_URL = "https://quote-garden.herokuapp.com/"
    }
}