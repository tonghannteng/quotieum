package com.tengtonghann.android.quotieum.di

import com.tengtonghann.android.quotieum.data.QuotieumService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
@Module
class QuotieumApiModule {

    fun provideRetrofitSingleton(): QuotieumService = Retrofit.Builder()
        .baseUrl("")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuotieumService::class.java)


    companion object {
        const val NETWORK_CALL_TIMEOUT = 60
    }
}