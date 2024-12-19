package com.htueko.tenki.core.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import com.htueko.tenki.core.presentation.theme.padding
import kotlin.math.roundToInt

/**
 * Composable function that displays a weather condition card with an icon, location name, wind direction, and temperature.
 *
 * @param modifier Modifier to be applied to the card.
 * @param iconUrl URL of the weather icon to be displayed.
 * @param weatherCondition Text description of the weather condition.
 * @param locationName Name of the location.
 * @param windDirection Angle in degrees representing the wind direction.
 * @param temperature Current temperature value.
 */
@Composable
fun WeatherConditionCard(
    modifier: Modifier = Modifier,
    iconUrl: String = "",
    weatherCondition: String = "",
    locationName: String = "",
    windDirection: Int = 0,
    temperature: Double = 0.0,
) {

    val paddingEight = MaterialTheme.padding.eight
    val paddingSixteen = MaterialTheme.padding.sixteen

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // weather icon
        NetworkImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            imageUrl = iconUrl,
            contentDescription = weatherCondition,
        )
        // location name text and wind direction icon row
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                top = paddingSixteen,
                bottom = paddingSixteen,
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            // location name text
            Text(
                text = locationName,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            // wind direction icon
            WindDirectionIcon(
                modifier = Modifier.padding(start = paddingEight),
                rotationDegrees = windDirection,
            )
        }
        // temperature text
        Text(
            text = "${temperature.roundToInt()}°",
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                fontSize = 70.sp,
                lineHeight = 80.sp,
                letterSpacing = 0.sp,
            ),
        )

    }
}

@Preview(name = "WeatherConditionCard")
@Composable
private fun PreviewWeatherConditionCard() {
    TenkiTheme {
        WeatherConditionCard(
            modifier = Modifier.fillMaxSize(),
            iconUrl = "https://pixabay.com/vectors/cute-cat-animal-forest-cartoon-7270285/",
            weatherCondition = "Sunny",
            locationName = "Tokyo",
            windDirection = 90,
            temperature = 25.0,
        )
    }
}