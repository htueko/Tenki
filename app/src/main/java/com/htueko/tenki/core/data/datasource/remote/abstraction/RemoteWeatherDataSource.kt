package com.htueko.tenki.core.data.datasource.remote.abstraction

import com.htueko.tenki.core.domain.model.dto.WeatherResponse
import com.htueko.tenki.core.domain.model.status.ResultOf

/**
 * Provides a remote data source for fetching current weather data by location.
 */
interface RemoteWeatherDataSource {
    /**
     * Fetches the current weather data for the specified location.
     *
     * @param location The location for which to fetch the weather data.
     * @return A [ResultOf] containing the [WeatherResponse] or an error if the fetch fails.
     */
    suspend fun getCurrentWeatherByLocation(location: String): ResultOf<WeatherResponse>
}