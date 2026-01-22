package com.htueko.tenki.core.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.htueko.tenki.core.presentation.theme.TenkiTheme

/**
 * Displays a network image with a loading indicator.
 *
 * @param modifier The modifier to be applied to the image.
 * @param imageUrl The URL of the image to be displayed.
 * @param contentDescription The content description for the image, used for accessibility.
 */
@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String? = null
) {

    val loadingState = rememberSaveable { mutableStateOf(true) }

    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            onSuccess = { loadingState.value = false },
            onError = { loadingState.value = false }
        )

        // to show loading indicator while the image is loading
        if (loadingState.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}


@Preview(name = "NetworkImage")
@Composable
private fun PreviewNetworkImage() {
    TenkiTheme {
        NetworkImage(
            modifier = Modifier.fillMaxSize(),
            imageUrl = "https://pixabay.com/vectors/cute-cat-animal-forest-cartoon-7270285/",
        )
    }
}