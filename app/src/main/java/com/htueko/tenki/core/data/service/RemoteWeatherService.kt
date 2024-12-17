package com.htueko.tenki.core.data.service

import com.htueko.tenki.core.domain.model.dto.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
 interface RemoteWeatherService {
    @GET("current.json")
    suspend fun getCurrentWeatherByLocation(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("aqi") aqi: String = "no"
    ): Response<WeatherResponse>
}