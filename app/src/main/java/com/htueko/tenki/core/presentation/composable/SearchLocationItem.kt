package com.htueko.tenki.core.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import com.htueko.tenki.core.presentation.theme.padding


/**
 * Composable function that renders a search location item with the provided city, temperature, weather icon, and condition.
 * The item is clickable and will call the [onClicked] callback with the city name when clicked.
 *
 * @param city The name of the city.
 * @param temperature The temperature of the location.
 * @param iconUrl The URL of the weather icon to display.
 * @param condition The weather condition of the location.
 * @param onClicked The callback function to be called when the item is clicked, passing the city name as a parameter.
 */
@Composable
fun SearchLocationItem(
    city: String,
    temperature: String,
    iconUrl: String,
    condition: String,
    onClicked: (locationName: String) -> Unit,
) {

    val paddingSixteen = MaterialTheme.padding.sixteen

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                onClicked(city)
            },
        color = MaterialTheme.colorScheme.surface,
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
            // Left Section: City and Temperature
            Column {
                Text(
                    text = city,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = temperature,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            // Right Section: Weather Icon
            NetworkImage(
                modifier = Modifier
                    .size(60.dp),
                imageUrl = iconUrl,
                contentDescription = condition,
            )
        }
    }
}

@Preview(name = "SearchLocationItem")
@Composable
private fun PreviewSearchLocationItem() {
    TenkiTheme {
        SearchLocationItem(
            city = "Tip Top",
            temperature = "dictum",
            iconUrl = "sample url",
            condition = "sunny",
            onClicked = {},
        )
    }
}