package com.htueko.tenki.core.data.repository

import com.htueko.tenki.core.data.datasource.remote.abstraction.RemoteWeatherDataSource
import com.htueko.tenki.core.data.mapper.remote.RemoteWeatherMapper
import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.SearchModel
import com.htueko.tenki.core.domain.model.status.ResultOf
import com.htueko.tenki.core.domain.repository.RemoteWeatherRepository
import com.htueko.tenki.core.util.getClassName
import com.htueko.tenki.core.util.logError
import com.htueko.tenki.core.util.logInfo
import javax.inject.Inject

/**
 * Implements the [RemoteWeatherRepository] interface, providing methods to retrieve current weather data and search for locations.
 *
 * @property remoteWeatherDataSource the data source for remote weather data
 */
class RemoteWeatherRepositoryImpl @Inject constructor(
    private val remoteWeatherDataSource: RemoteWeatherDataSource
) : RemoteWeatherRepository {
    private val tag = getClassName<RemoteWeatherRepositoryImpl>()
    /**
     * Retrieves the current weather for the specified location.
     *
     * @param location the location to retrieve the current weather for
     * @return a [ResultOf] object containing either the [CurrentWeather] data or an error
     */
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

    /**
     * Searches for locations based on the provided query string and retrieves the current weather for each location.
     *
     * @param query the search query string
     * @return a [ResultOf] object containing a list of [SearchModel] instances or an error
     */
    override suspend fun searchLocation(query: String): ResultOf<List<SearchModel>> {
        return when (val result = remoteWeatherDataSource.searchLocation(query)) {
            is ResultOf.Success -> {
                val tempSearchModelList = mutableListOf<SearchModel>()
                result.data.forEach {
                    when (val response =
                        remoteWeatherDataSource.getCurrentWeatherByLocation(it.name)) {
                        is ResultOf.ApiError -> {
                            logError(
                                tag,
                                "getCurrentWeatherByLocation for location name ${it.name} api error: ${response.message}"
                            )
                        }

                        is ResultOf.NetworkError -> {
                            logError(
                                tag,
                                "getCurrentWeatherByLocation for location name: ${it.name} network error: ${response.throwable.message}"
                            )
                        }

                        is ResultOf.Success -> {
                            val searchModel =
                                RemoteWeatherMapper.weatherResponseToSearchModel(response.data)
                            tempSearchModelList.add(searchModel)
                        }
                    }
                }
                logInfo(tag, "searchLocation list success: $tempSearchModelList")
                ResultOf.Success(tempSearchModelList)
            }

            is ResultOf.ApiError -> {
                logError(tag, "searchLocation api error: ${result.message}")
                ResultOf.ApiError(result.message)
            }

            is ResultOf.NetworkError -> {
                logError(
                    tag,
                    "searchLocation network error: ${result.throwable.message}"
                )
                ResultOf.NetworkError(result.throwable)
            }
        }
    }

}