package com.htueko.tenki.core.domain.model

data class CurrentWeather(
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
    val windDegree: Int = 0,
)