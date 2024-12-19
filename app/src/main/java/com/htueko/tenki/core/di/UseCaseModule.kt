package com.htueko.tenki.core.di

import com.htueko.tenki.core.domain.usecase.dataStoreUsecase.GetLocationNameUseCase
import com.htueko.tenki.core.domain.usecase.dataStoreUsecase.GetLocationNameUseCaseImpl
import com.htueko.tenki.core.domain.usecase.dataStoreUsecase.SetLocationNameUseCase
import com.htueko.tenki.core.domain.usecase.dataStoreUsecase.SetLocationNameUseCaseImpl
import com.htueko.tenki.core.domain.usecase.weatherUsecase.GetCurrentWeatherByLocationUseCase
import com.htueko.tenki.core.domain.usecase.weatherUsecase.GetCurrentWeatherByLocationUseCaseImpl
import com.htueko.tenki.core.domain.usecase.weatherUsecase.SearchLocationUseCase
import com.htueko.tenki.core.domain.usecase.weatherUsecase.SearchLocationUseCaseImpl
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

    /**
     * Binds the implementation of [GetLocationNameUseCase] to the abstract type.
     * This allows Dagger to provide the appropriate implementation when the abstract
     * type is requested.
     *
     * @param impl The implementation of [GetLocationNameUseCase] to be bound.
     * @return The bound [GetLocationNameUseCase] implementation.
     */
    @Binds
    abstract fun bindGetLocationNameUseCase(impl: GetLocationNameUseCaseImpl): GetLocationNameUseCase

    /**
     * Binds the implementation of [SetLocationNameUseCase] to the abstract type.
     * This allows Dagger to provide the appropriate implementation when the abstract
     * type is requested.
     *
     * @param impl The implementation of [SetLocationNameUseCase] to be bound.
     * @return The bound [SetLocationNameUseCase] implementation.
     */
    @Binds
    abstract fun bindSetLocationNameUseCase(impl: SetLocationNameUseCaseImpl): SetLocationNameUseCase

    /**
     * Binds the implementation of [SearchLocationUseCase] to the abstract type.
     * This allows Dagger to provide the appropriate implementation when the abstract
     * type is requested.
     *
     * @param impl The implementation of [SearchLocationUseCase] to be bound.
     * @return The bound [SearchLocationUseCase] implementation.
     */
    @Binds
    abstract fun bindSearchLocationUseCase(impl: SearchLocationUseCaseImpl): SearchLocationUseCase
}