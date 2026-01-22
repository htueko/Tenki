package com.htueko.tenki.core.domain.model.dto

import com.squareup.moshi.Json

data class LocationResponse(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "region")
    val region: String = "",
    @Json(name = "country")
    val country: String = "",
    @Json(name = "lat")
    val lat: Double = 0.0,
    @Json(name = "lon")
    val lon: Double = 0.0,
    @Json(name = "url")
    val url: String = ""
)