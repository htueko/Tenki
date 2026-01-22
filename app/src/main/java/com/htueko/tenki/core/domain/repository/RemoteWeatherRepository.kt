package com.htueko.tenki.core.domain.repository

import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.SearchModel
import com.htueko.tenki.core.domain.model.dto.LocationResponse
import com.htueko.tenki.core.domain.model.status.ResultOf

/**
 * Defines the contract for a remote weather repository that provides access to weather data.
 *
 * @property getCurrentWeatherByLocation Retrieves the current weather for the given location.
 * @property searchLocation Searches for locations based on the provided query.
 */
interface RemoteWeatherRepository {
    /**
     * Retrieves the current weather for the given location.
     *
     * @param location The location for which to retrieve the current weather.
     * @return A [ResultOf] containing the [CurrentWeather] data, or an error if the operation fails.
     */
    suspend fun getCurrentWeatherByLocation(location: String): ResultOf<CurrentWeather>
    /**
     * Searches for locations based on the provided query.
     *
     * @param query The search query to use for location search.
     * @return A [ResultOf] containing a list of [SearchModel] representing the search results, or an error if the operation fails.
     */
    suspend fun searchLocation(query: String): ResultOf<List<SearchModel>>
}