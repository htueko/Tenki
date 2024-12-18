package com.htueko.tenki.feature.home.event

/**
 * Sealed class representing events that can occur in the home feature.
 *
 * @property OnSearchIconClicked Represents the event when the search icon is clicked.
 * @property GetCurrentWeatherByLocation Represents the event to get the current weather by location.
 *     @param name The name of the location.
 */
sealed class HomeEvent {
    data object OnSearchIconClicked: HomeEvent()
    data class GetCurrentWeatherByLocation(val name: String) : HomeEvent()
}