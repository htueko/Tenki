package com.htueko.tenki.core.domain.model.dto

import com.squareup.moshi.Json

data class LocationResponse(
    @param:Json(name = "id")
    val id: Int = 0,
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "region")
    val region: String = "",
    @param:Json(name = "country")
    val country: String = "",
    @param:Json(name = "lat")
    val lat: Double = 0.0,
    @param:Json(name = "lon")
    val lon: Double = 0.0,
    @param:Json(name = "url")
    val url: String = ""
)