package com.htueko.tenki.core.di

import com.htueko.tenki.core.data.repository.RemoteWeatherRepositoryImpl
import com.htueko.tenki.core.domain.repository.RemoteWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    /**
     * Binds the implementation of [RemoteWeatherRepository] to the abstract type.
     * This allows Dagger to provide the appropriate implementation when the abstract
     * type is requested.
     *
     * @param impl The implementation of [RemoteWeatherRepository] to be bound.
     * @return The bound [RemoteWeatherRepository] implementation.
     */
    @Binds
    abstract fun bindRemoteWeatherRepository(impl: RemoteWeatherRepositoryImpl): RemoteWeatherRepository
}