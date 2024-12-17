package com.htueko.tenki.core.data.repository

import com.htueko.tenki.core.data.datasource.remote.abstraction.RemoteWeatherDataSource
import com.htueko.tenki.core.data.mapper.remote.RemoteWeatherMapper
import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.status.ResultOf
import com.htueko.tenki.core.domain.repository.RemoteWeatherRepository
import com.htueko.tenki.core.util.getClassName
import com.htueko.tenki.core.util.logError
import com.htueko.tenki.core.util.logInfo
import javax.inject.Inject

class RemoteWeatherRepositoryImpl @Inject constructor(
    private val remoteWeatherDataSource: RemoteWeatherDataSource
) : RemoteWeatherRepository {
    private val tag = getClassName<RemoteWeatherRepositoryImpl>()
    override suspend fun getCurrentWeatherByLocation(location: String): ResultOf<CurrentWeather> {
        return when (val result = remoteWeatherDataSource.getCurrentWeatherByLocation(location)) {
            is ResultOf.Success -> {
                val weatherResponse = result.data
                logInfo(tag, "getCurrentWeatherByLocation success: $weatherResponse")
                ResultOf.Success(RemoteWeatherMapper.weatherResponseToCurrentWeather(weatherResponse))
            }

            is ResultOf.ApiError -> {
                logError(tag, "getCurrentWeatherByLocation api error: ${result.message}")
                ResultOf.ApiError(result.message)
            }

            is ResultOf.NetworkError -> {
                logError(
                    tag,
                    "getCurrentWeatherByLocation network error: ${result.throwable.message}"
                )
                ResultOf.NetworkError(result.throwable)
            }
        }
    }

}