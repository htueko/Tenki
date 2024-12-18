package com.htueko.tenki.feature.home.state

import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.SearchModel

/**
 * Represents the UI state for the home screen of the Tenki weather app.
 *
 * @property isLoading Indicates whether the app is currently loading data.
 * @property hasPreviousLocation Indicates whether the app has a previously saved location.
 * @property isSearching Indicates whether the user is currently searching for a location.
 * @property hasQueryResult Indicates whether the search query has returned any results.
 * @property searchLocationList A list of search results for the current query.
 * @property name The name of the current location.
 * @property tempC The current temperature in Celsius.
 * @property tempF The current temperature in Fahrenheit.
 * @property feelsLikeC The current "feels like" temperature in Celsius.
 * @property feelsLikeF The current "feels like" temperature in Fahrenheit.
 * @property icon The icon representing the current weather conditions.
 * @property humidity The current humidity level.
 * @property description A description of the current weather conditions.
 * @property vu The current UV index.
 * @property lastUpdated The timestamp of the last weather data update.
 * @property windDirection The current wind direction in degrees.
 */
data class HomeUiState(
    val isLoading: Boolean = true,
    val hasPreviousLocation: Boolean = false,
    val isSearching: Boolean = false,
    val hasQueryResult: Boolean = false,
    val searchLocationList: List<SearchModel> = emptyList(),
    val name: String = "",
    val tempC: Double = 0.0,
    val tempF: Double = 0.0,
    val feelsLikeC: Double = 0.0,
    val feelsLikeF: Double = 0.0,
    val icon: String = "",
    val humidity: Int = 0,
    val description: String = "",
    val vu: Double = 0.0,
    val lastUpdated: String = "",
    val windDirection: Int = 0,
)