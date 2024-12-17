package com.htueko.tenki.feature.home.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import com.htueko.tenki.feature.home.state.HomeUiState

@Composable
internal fun HomeContent(
    viewState: HomeUiState,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = viewState.currentWeather.description
        )
    }
}

@Preview
@Composable
private fun HomeContentPreview() {
    TenkiTheme {
        HomeContent(
            viewState = HomeUiState(
                isLoading = false,
                currentWeather = CurrentWeather(
                    name = "Les McCray",
                    tempC = 30.31,
                    tempF = 32.33,
                    feelsLikeC = 34.35,
                    feelsLikeF = 36.37,
                    icon = "taciti",
                    humidity = 5782,
                    description = "populo",
                    vu = 38.39,
                    lastUpdated = "autem"
                )
            ),
        )
    }
}