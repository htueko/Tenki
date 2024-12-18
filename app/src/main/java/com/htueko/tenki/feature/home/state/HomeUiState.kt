package com.htueko.tenki.feature.home.state

import com.htueko.tenki.core.domain.model.CurrentWeather

data class HomeUiState(
    val isLoading: Boolean = true,
    val hasPreviousLocation: Boolean = false,
    val hasFocus: Boolean = false,
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