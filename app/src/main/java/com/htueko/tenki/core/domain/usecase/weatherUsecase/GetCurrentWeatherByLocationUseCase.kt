package com.htueko.tenki.core.domain.usecase.weatherUsecase

import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.status.ResultOf

interface GetCurrentWeatherByLocationUseCase {
    suspend operator fun invoke(
        location: String,
    ): ResultOf<CurrentWeather>
}