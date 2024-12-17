package com.htueko.tenki.core.domain.usecase.weatherUsecase

import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.status.ResultOf
import com.htueko.tenki.core.domain.repository.RemoteWeatherRepository
import javax.inject.Inject

class GetCurrentWeatherByLocationUseCaseImpl @Inject constructor(
    private val remoteWeatherRepository: RemoteWeatherRepository,
): GetCurrentWeatherByLocationUseCase {
    override suspend fun invoke(location: String): ResultOf<CurrentWeather> {
        return remoteWeatherRepository.getCurrentWeatherByLocation(location)
    }
}