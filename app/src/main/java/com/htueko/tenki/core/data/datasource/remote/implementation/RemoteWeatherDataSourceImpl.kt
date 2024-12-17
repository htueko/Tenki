package com.htueko.tenki.core.data.datasource.remote.implementation

import com.htueko.tenki.core.data.datasource.remote.abstraction.RemoteWeatherDataSource
import com.htueko.tenki.core.data.service.RemoteWeatherService
import com.htueko.tenki.core.di.ApiKey
import com.htueko.tenki.core.domain.model.dto.WeatherResponse
import com.htueko.tenki.core.domain.model.status.ResultOf
import com.htueko.tenki.core.util.getClassName
import com.htueko.tenki.core.util.logError
import com.htueko.tenki.core.util.logInfo
import javax.inject.Inject



class RemoteWeatherDataSourceImpl @Inject constructor(
    private val remoteWeatherService: RemoteWeatherService,
    @ApiKey
    private val apiKey: String
) : RemoteWeatherDataSource {

    private val tag = getClassName<RemoteWeatherDataSourceImpl>()

    /**
     * Retrieves the current weather for the specified location.
     *
     * @param location The location for which to retrieve the current weather.
     * @return A [ResultOf] object containing either a successful [WeatherResponse] or an error.
     */
    override suspend fun getCurrentWeatherByLocation(location: String): ResultOf<WeatherResponse> {
        return try {
            val response = remoteWeatherService.getCurrentWeatherByLocation(apiKey, location)
            logInfo(tag, "getCurrentWeatherByLocation response: $response")
            if (response.isSuccessful) {
                if (response.body() == null) {
                    logError(tag, "getCurrentWeatherByLocation response body is null: $response")
                    return ResultOf.ApiError(response.message())
                }
                logInfo(tag, "getCurrentWeatherByLocation response body: ${response.body()}")
                ResultOf.Success(response.body()!!)
            } else {
                logError(
                    tag,
                    "getCurrentWeatherByLocation response api error: ${response.message()}"
                )
                ResultOf.ApiError(response.message())
            }
        } catch (e: Exception) {
            logError(tag, "getCurrentWeatherByLocation exception: $e")
            ResultOf.NetworkError(e)
        }
    }
}