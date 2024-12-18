package com.htueko.tenki.feature.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.htueko.tenki.R
import com.htueko.tenki.core.presentation.composable.FullScreenLoadingIndicator
import com.htueko.tenki.core.presentation.composable.LocationTextField
import com.htueko.tenki.core.presentation.composable.WeatherConditionCard
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import com.htueko.tenki.core.presentation.theme.padding
import com.htueko.tenki.feature.home.state.HomeUiState

@Composable
internal fun HomeContent(
    viewState: HomeUiState,
    locationNameValue: String,
    onLocationNameValueChange: (String) -> Unit,
    onSearchIconClicked: () -> Unit,
) {

    val paddingEight = MaterialTheme.padding.eight
    val paddingSixteen = MaterialTheme.padding.sixteen
    val paddingTwentyFour = MaterialTheme.padding.twentyFour

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            // to show the full screen loading indicator
            when (viewState.isLoading) {
                true -> {
                    FullScreenLoadingIndicator()
                }

                false -> {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                    ) {

                        val centerColumnGuideLine = createGuidelineFromTop(0.5f)

                        val (
                            locationTextField,
                            noLocationTextTitle,
                            noLocationTextDescription,
                            weatherConditionCard,
                            weatherInfoCard,
                            locationList,
                        ) = createRefs()

                        // search text field
                        LocationTextField(
                            modifier = Modifier.constrainAs(locationTextField) {
                                start.linkTo(parent.start, margin = paddingSixteen)
                                end.linkTo(parent.end, margin = paddingSixteen)
                                width = Dimension.fillToConstraints
                                top.linkTo(parent.top, margin = paddingTwentyFour)
                            },
                            value = locationNameValue,
                            onValueChange = onLocationNameValueChange,
                            placeholder = stringResource(id = R.string.location_hint_text),
                            onFocusChange = {},
                            onSearchIconClicked = onSearchIconClicked,
                        )

                        // to show no location text or not
                        if (viewState.hasPreviousLocation) {
                            WeatherConditionCard(
                                modifier = Modifier.constrainAs(weatherConditionCard) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(locationTextField.bottom, margin = paddingTwentyFour)
                                    bottom.linkTo(centerColumnGuideLine)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                },
                                iconUrl = viewState.icon,
                                weatherCondition = viewState.description,
                                locationName = viewState.name,
                                windDirection = viewState.windDirection,
                                temperature = viewState.tempC,
                            )
                        } else {
                            // no location section
                            // no location text title
                            Text(
                                modifier = Modifier.constrainAs(noLocationTextTitle) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                    bottom.linkTo(centerColumnGuideLine)
                                },
                                text = stringResource(id = R.string.no_location_text_title),
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                            // no location text description
                            Text(
                                modifier = Modifier.constrainAs(noLocationTextDescription) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                    top.linkTo(noLocationTextTitle.bottom, margin = paddingSixteen)
                                },
                                text = stringResource(id = R.string.no_location_text_desc),
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        }

                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun HomeContentPreview() {
    TenkiTheme {
        HomeContent(
            viewState = HomeUiState(
                isLoading = false,
                hasPreviousLocation = true,
                name = "Les McCray",
                tempC = 30.31,
                tempF = 32.33,
                feelsLikeC = 34.35,
                feelsLikeF = 36.37,
                icon = "taciti",
                humidity = 5782,
                description = "populo",
                vu = 38.39,
                lastUpdated = "autem",
            ),
            locationNameValue = "",
            onLocationNameValueChange = {},
            onSearchIconClicked = {},
        )
    }
}