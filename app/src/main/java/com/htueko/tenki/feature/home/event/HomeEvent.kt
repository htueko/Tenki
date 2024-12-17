package com.htueko.tenki.feature.home.event

sealed class HomeEvent {
    data class GetCurrentWeatherByLocation(val name: String) : HomeEvent()
}