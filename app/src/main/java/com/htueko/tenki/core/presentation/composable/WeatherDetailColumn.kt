package com.htueko.tenki.core.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.htueko.tenki.core.presentation.theme.DimGrey
import com.htueko.tenki.core.presentation.theme.SilverGrey
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import com.htueko.tenki.core.presentation.theme.padding

/**
 * Composable function that displays a column with a title and a value.
 *
 * @param title The title to be displayed.
 * @param value The value to be displayed.
 */
@Composable
fun WeatherDetailColumn(
    title: String,
    value: String,
) {

    val paddingEight = MaterialTheme.padding.eight

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = SilverGrey,
            fontSize = 12.sp,
            style = MaterialTheme.typography.bodySmall,
        )

        Spacer(modifier = Modifier.height(paddingEight))

        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DimGrey,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}


@Preview(name = "WeatherDetailColumn")
@Composable
private fun PreviewWeatherDetailColumn() {
    TenkiTheme {
        WeatherDetailColumn(
            title = "title",
            value = "value",
        )
    }
}