package com.htueko.tenki.core.data.mapper.remote

import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.dto.WeatherResponse

object RemoteWeatherMapper {
    fun weatherResponseToCurrentWeather(dto: WeatherResponse) = CurrentWeather(
        name = dto.location.name,
        tempC = dto.current.tempC,
        tempF = dto.current.tempF,
        feelsLikeC = dto.current.feelslikeC,
        feelsLikeF = dto.current.feelslikeF,
        icon = "https:${dto.current.condition.icon.replace("64x64", "128x128")}",
        humidity = dto.current.humidity,
        description = dto.current.condition.text,
        vu = dto.current.uv,
        lastUpdated = dto.current.lastUpdated,
        windDegree = dto.current.windDegree,
    )
}