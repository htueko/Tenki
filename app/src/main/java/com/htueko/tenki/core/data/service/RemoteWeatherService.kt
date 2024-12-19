package com.htueko.tenki.core.data.service

import com.htueko.tenki.core.domain.model.dto.LocationResponse
import com.htueko.tenki.core.domain.model.dto.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteWeatherService {
    /**
     * Retrieves the current weather for a given location.
     *
     * @param apiKey The API key to authenticate the request.
     * @param location The location for which to retrieve the weather.
     * @param aqi Whether to include air quality information in the response. Defaults to "no".
     * @return A [Response] containing the [WeatherResponse] for the specified location.
     */
    @GET("current.json")
    suspend fun getCurrentWeatherByLocation(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("aqi") aqi: String = "no"
    ): Response<WeatherResponse>

    /**
     * Searches for a location based on the provided query string.
     *
     * @param apiKey The API key to authenticate the request.
     * @param query The search query to use for the location lookup.
     * @return A [Response] containing a list of [LocationResponse] objects matching the search query.
     */
    @GET("search.json")
    suspend fun searchLocation(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): Response<List<LocationResponse>>
}