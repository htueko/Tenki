package com.htueko.tenki.core.di

import com.htueko.tenki.core.data.datasource.local.abstraction.DataStoreDataSource
import com.htueko.tenki.core.data.datasource.local.implementation.DataStoreDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    /**
     * Binds the implementation of [DataStoreDataSource] to the abstract type.
     * This allows Dagger to provide the appropriate implementation when the abstract
     * type is requested.
     *
     * @param impl The implementation of [DataStoreDataSource] to be bound.
     * @return The bound [DataStoreDataSource] implementation.
     */
    @Binds
    abstract fun bindDataStoreDataSource(impl: DataStoreDataSourceImpl): DataStoreDataSource
}