package com.htueko.tenki.core.di

import com.htueko.tenki.core.data.datasource.remote.abstraction.RemoteWeatherDataSource
import com.htueko.tenki.core.data.datasource.remote.implementation.RemoteWeatherDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    /**
     * Binds the implementation of [RemoteWeatherDataSource] to the abstract type.
     * This allows Dagger to provide the appropriate implementation when the abstract
     * type is requested.
     *
     * @param impl The implementation of [RemoteWeatherDataSource] to be bound.
     * @return The bound [RemoteWeatherDataSource] implementation.
     */
    @Binds
    abstract fun bindRemoteWeatherDataSource(impl: RemoteWeatherDataSourceImpl): RemoteWeatherDataSource
}