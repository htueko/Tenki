package com.htueko.tenki.core.di

import com.htueko.tenki.core.domain.usecase.weatherUsecase.GetCurrentWeatherByLocationUseCase
import com.htueko.tenki.core.domain.usecase.weatherUsecase.GetCurrentWeatherByLocationUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    /**
     * Binds the implementation of [GetCurrentWeatherByLocationUseCase] to the abstract type.
     * This allows Dagger to provide the appropriate implementation when the abstract
     * type is requested.
     *
     * @param impl The implementation of [GetCurrentWeatherByLocationUseCase] to be bound.
     * @return The bound [GetCurrentWeatherByLocationUseCase] implementation.
     */
    @Binds
    abstract fun bindGetCurrentWeatherByLocationUseCase(impl: GetCurrentWeatherByLocationUseCaseImpl): GetCurrentWeatherByLocationUseCase
}