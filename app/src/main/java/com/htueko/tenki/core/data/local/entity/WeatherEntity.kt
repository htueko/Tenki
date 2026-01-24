package com.htueko.tenki.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.htueko.tenki.core.constant.LocalConstant

@Entity(
    tableName = LocalConstant.WEATHER_TABLE_NAME
)
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val name: String = "",
    val region: String = "",
    val country: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val timeZoneId: String = "",
    val localTimeEpoch: Long = 0L,
    val localTimeString: String = "",
    val lastUpdatedEpoch: Long = 0L,
    val lastUpdatedTimeString: String = "",
    val tempC: Double = 0.0,
    val tempF: Double = 0.0,
    val isDayTime: Boolean = true,
    val weatherCondition: String = "",
    val icon: String = "",
    val iconCode: Int = 0,
    val windMph: Double = 0.0,
    val windKph: Double = 0.0,
    val windDegree: Int = 0,
    val windDirection: String = "",
    val humidity: Int = 0,
    val cloud: Int = 0,
    val presureMb: Double = 0.0,
    val presureIn: Double = 0.0,
    val precipitationMM: Double = 0.0,
    val precipitationIn: Double = 0.0,
    val feelsLikeC: Double = 0.0,
    val feelsLikeF: Double = 0.0,
    val windChillC: Double = 0.0,
    val windChillF: Double = 0.0,
    val heatIndexC: Double = 0.0,
    val heatIndexF: Double = 0.0,
    val dewPointC: Double = 0.0,
    val dewPointF: Double = 0.0,
    val visibilityKilometers: Double = 0.0,
    val uvIndex: Double = 0.0,
    val gustMph: Double = 0.0,
    val gustKph: Double = 0.0,
)