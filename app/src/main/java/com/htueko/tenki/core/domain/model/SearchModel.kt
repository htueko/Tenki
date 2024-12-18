package com.htueko.tenki.core.domain.model

data class SearchModel(
    val iconUrl: String = "",
    val name: String = "",
    val tempC: Double = 0.0,
    val tempF: Double = 0.0,
    val condition: String = "",
)