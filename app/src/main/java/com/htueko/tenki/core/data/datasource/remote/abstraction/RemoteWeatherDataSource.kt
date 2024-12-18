package com.htueko.tenki.core.data.datasource.remote.abstraction

import com.htueko.tenki.core.domain.model.dto.LocationResponse
import com.htueko.tenki.core.domain.model.dto.WeatherResponse
import com.htueko.tenki.core.domain.model.status.ResultOf
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

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

    /**
     * Searches for locations that match the specified query.
     *
     * @param query The search query to use for finding locations.
     * @return A [ResultOf] containing a list of [LocationResponse] objects representing the matching locations, or an error if the search fails.
     */
    suspend fun searchLocation(query: String): ResultOf<List<LocationResponse>>
}