package com.htueko.tenki.core.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.htueko.tenki.R
import com.htueko.tenki.core.presentation.theme.TenkiTheme

/**
 * Displays an icon representing the wind direction, with the icon rotated by the specified number of degrees.
 *
 * @param modifier The modifier to be applied to the image.
 * @param rotationDegrees The number of degrees to rotate the icon.
 */
@Composable
fun WindDirectionIcon(
    modifier: Modifier = Modifier,
    rotationDegrees: Int = 0,
) {
    Image(
        modifier = modifier.graphicsLayer(
            rotationZ = rotationDegrees.toFloat()
        ),
        painter = painterResource(id = R.drawable.ic_navigation),
        contentDescription = rotationDegrees.toString(),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
    )
}


@Preview(name = "WindDirectionIcon")
@Composable
private fun PreviewWindDirectionIcon() {
    TenkiTheme {
        WindDirectionIcon(
            rotationDegrees = 125,
        )
    }
}