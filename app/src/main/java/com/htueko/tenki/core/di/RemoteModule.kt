package com.htueko.tenki.core.di

import com.htueko.tenki.core.constant.RemoteApiConstant
import com.htueko.tenki.core.data.interceptor.ApiKeyInterceptor
import com.htueko.tenki.core.data.service.RemoteWeatherService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * This module provides the necessary dependencies for remote API interactions.
 * It provides base URL, OkHttpClient, Retrofit, and RemoteApiService.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    /**
     * Provides the API key to be used for making requests to the remote API.
     *
     * @return The API key as a String.
     */
    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(): String {
        return RemoteApiConstant.WEATHER_API_KEY
    }

    /**
     * Provides the base URL for the remote API.
     *
     * @return The base URL as a String.
     */
    @Provides
    fun provideBaseUrl() = RemoteApiConstant.BASE_URL

    /**
     * Provides an OkHttpClient used for making HTTP requests.
     *
     * @return An instance of OkHttpClient.
     */
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient
            .Builder()
            .addInterceptor(ApiKeyInterceptor(provideApiKey()))
            .build()

    /**
     * Provides an instance of Moshi, a JSON parsing library, with the KotlinJsonAdapterFactory
     * added to handle Kotlin-specific types.
     *
     * @return An instance of Moshi configured with the KotlinJsonAdapterFactory.
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    /**
     * Provides a Retrofit instance used for making API requests.
     *
     * @param okHttpClient The OkHttpClient instance to be used.
     * @return An instance of Retrofit.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(provideBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .client(okHttpClient)
            .build()
    }

    /**
     * Provides an instance of RemoteWeatherApiService.
     *
     * @param retrofit The Retrofit instance to be used.
     * @return An instance of RemoteApiService.
     */
    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): RemoteWeatherService =
        retrofit.create(RemoteWeatherService::class.java)

}