package com.htueko.tenki.feature.home.state

import com.htueko.tenki.core.domain.model.CurrentWeather

data class HomeUiState(
    val isLoading: Boolean = true,
    val currentWeather: CurrentWeather = CurrentWeather(),
)