package com.htueko.tenki.core.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.htueko.tenki.R
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import com.htueko.tenki.core.presentation.theme.padding

/**
 * Composable function that renders a weather details card with information about humidity, UV index, and feels like temperature.
 *
 * @param modifier Optional modifier to be applied to the card.
 * @param humidityValue The humidity value to be displayed.
 * @param uvValue The UV index value to be displayed.
 * @param feelsLikeValue The feels like temperature value to be displayed.
 */
@Composable
fun WeatherDetailsCard(
    modifier: Modifier = Modifier,
    humidityValue: String,
    uvValue: String,
    feelsLikeValue: String,
) {

    val paddingSixteen = MaterialTheme.padding.sixteen

    Surface(
        color = Color(0xFFF5F5F5),
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = paddingSixteen),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Humidity Column
            WeatherDetailColumn(
                title = stringResource(R.string.humidity),
                value = "$humidityValue%"
            )

            // UV Column
            WeatherDetailColumn(title = stringResource(R.string.uv), value = uvValue)

            // Feels Like Column
            WeatherDetailColumn(
                title = stringResource(R.string.feels_like),
                value = "$feelsLikeValue°"
            )
        }
    }
}

@Preview(name = "WeatherDetailColumn")
@Composable
private fun PreviewWeatherDetailCard() {
    TenkiTheme {
        WeatherDetailsCard(
            humidityValue = "75",
            uvValue = "5",
            feelsLikeValue = "25"
        )
    }
}


