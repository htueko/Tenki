package com.htueko.tenki.core.domain.usecase.weatherUsecase

import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.status.ResultOf

/**
 * Retrieves the current weather for the specified location.
 *
 * @param location The location for which to retrieve the current weather.
 * @return A [ResultOf] containing the [CurrentWeather] data, or an error if the operation fails.
 */
interface GetCurrentWeatherByLocationUseCase {
    suspend operator fun invoke(
        location: String,
    ): ResultOf<CurrentWeather>
}