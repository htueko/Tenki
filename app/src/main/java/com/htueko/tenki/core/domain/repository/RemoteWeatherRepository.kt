package com.htueko.tenki.core.domain.repository

import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.status.ResultOf

interface RemoteWeatherRepository {
    suspend fun getCurrentWeatherByLocation(location: String): ResultOf<CurrentWeather>
}