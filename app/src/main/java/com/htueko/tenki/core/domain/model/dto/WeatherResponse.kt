package com.htueko.tenki.core.domain.model.dto

import com.squareup.moshi.Json

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String = "",
    val region: String = "",
    val country: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    @Json(name = "tz_id")
    val tzId: String = "",
    @Json(name = "localtime_epoch")
    val localtimeEpoch: Long = 0,
    val localtime: String = ""
)

data class Current(
    @Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Long = 0,
    @Json(name = "last_updated")
    val lastUpdated: String = "",
    @Json(name = "temp_c")
    val tempC: Double = 0.0,
    @Json(name ="temp_f")
    val tempF: Double = 0.0,
    @Json(name = "is_day")
    val isDay: Int = 0,
    val condition: Condition = Condition(),
    @Json(name = "wind_mph")
    val windMph: Double = 0.0,
    @Json(name = "wind_kph")
    val windKph: Double = 0.0,
    @Json(name = "wind_degree")
    val windDegree: Int = 0,
    @Json(name = "wind_dir")
    val windDir: String = "",
    @Json(name = "pressure_mb")
    val pressureMb: Double = 0.0,
    @Json(name = "pressure_in")
    val pressureIn: Double = 0.0,
    @Json(name = "precip_mm")
    val precipMm: Double = 0.0,
    @Json(name = "precip_in")
    val precipIn: Double = 0.0,
    val humidity: Int = 0,
    val cloud: Int = 0,
    @Json(name = "feelslike_c")
    val feelslikeC: Double = 0.0,
    @Json(name = "feelslike_f")
    val feelslikeF: Double = 0.0,
    @Json(name = "windchill_c")
    val windchillC: Double = 0.0,
    @Json(name = "windchill_f")
    val windchillF: Double = 0.0,
    @Json(name = "heatindex_c")
    val heatindexC: Double = 0.0,
    @Json(name = "heatindex_f")
    val heatindexF: Double = 0.0,
    @Json(name = "dewpoint_c")
    val dewpointC: Double = 0.0,
    @Json(name = "dewpoint_f")
    val dewpointF: Double = 0.0,
    @Json(name = "vis_km")
    val visKm: Double = 0.0,
    @Json(name = "vis_miles")
    val visMiles: Double = 0.0,
    val uv: Double = 0.0,
    @Json(name = "gust_mph")
    val gustMph: Double = 0.0,
    @Json(name = "gust_kph")
    val gustKph: Double = 0.0,
    @Json(name = "air_quality")
    val airQuality: AirQuality = AirQuality()
)

data class Condition(
    val text: String = "",
    val icon: String = "",
    val code: Int = 0
)

data class AirQuality(
    val co: Double = 0.0,
    val no2: Double = 0.0,
    val o3: Double = 0.0,
    val so2: Double = 0.0,
    @Json(name = "pm2_5")
    val pm2_5: Double = 0.0,
    @Json(name = "pm10")
    val pm10: Double = 0.0,
    @Json(name = "us-epa-index")
    val usEpaIndex: Int = 0,
    @Json(name = "gb-defra-index")
    val gbDefraIndex: Int = 0
)