package com.htueko.tenki.core.domain.model.dto

import com.squareup.moshi.Json

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    @Json(name = "tz_id")
    val tzId: String,
    @Json(name = "localtime_epoch")
    val localtimeEpoch: Long,
    val localtime: String
)

data class Current(
    @Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Long,
    @Json(name = "last_updated")
    val lastUpdated: String,
    @Json(name = "temp_c")
    val tempC: Double,
    @Json(name = "temp_f")
    val tempF: Double,
    @Json(name = "is_day")
    val isDay: Int,
    val condition: Condition,
    @Json(name = "wind_mph")
    val windMph: Double,
    @Json(name = "wind_kph")
    val windKph: Double,
    @Json(name = "wind_degree")
    val windDegree: Int,
    @Json(name = "wind_dir")
    val windDir: String,
    @Json(name = "pressure_mb")
    val pressureMb: Double,
    @Json(name = "pressure_in")
    val pressureIn: Double,
    @Json(name = "precip_mm")
    val precipMm: Double,
    @Json(name = "precip_in")
    val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    @Json(name = "feelslike_c")
    val feelslikeC: Double,
    @Json(name = "feelslike_f")
    val feelslikeF: Double,
    @Json(name = "windchill_c")
    val windchillC: Double,
    @Json(name = "windchill_f")
    val windchillF: Double,
    @Json(name = "heatindex_c")
    val heatindexC: Double,
    @Json(name = "heatindex_f")
    val heatindexF: Double,
    @Json(name = "dewpoint_c")
    val dewpointC: Double,
    @Json(name = "dewpoint_f")
    val dewpointF: Double,
    @Json(name = "vis_km")
    val visKm: Double,
    @Json(name = "vis_miles")
    val visMiles: Double,
    val uv: Double,
    @Json(name = "gust_mph")
    val gustMph: Double,
    @Json(name = "gust_kph")
    val gustKph: Double,
    @Json(name = "air_quality")
    val airQuality: AirQuality
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

data class AirQuality(
    val co: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    @Json(name = "pm2_5") val pm2_5: Double,
    @Json(name = "pm10") val pm10: Double,
    @Json(name = "us-epa-index") val usEpaIndex: Int,
    @Json(name = "gb-defra-index") val gbDefraIndex: Int
)