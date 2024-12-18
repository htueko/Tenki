package com.htueko.tenki.feature.home.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.htueko.tenki.core.presentation.composable.SearchLocationItem
import com.htueko.tenki.core.presentation.composable.WeatherConditionCard
import com.htueko.tenki.core.presentation.composable.WeatherDetailsCard
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import com.htueko.tenki.core.presentation.theme.padding
import com.htueko.tenki.feature.home.state.HomeUiState
import kotlin.math.roundToInt

@Composable
internal fun HomeContent(
    viewState: HomeUiState,
    locationNameValue: String,
    onLocationNameValueChange: (String) -> Unit,
    onSearchIconClicked: () -> Unit,
    onSearchItemClicked: (locationName: String) -> Unit,
) {

    val paddingEight = MaterialTheme.padding.eight
    val paddingSixteen = MaterialTheme.padding.sixteen
    val paddingTwentyFour = MaterialTheme.padding.twentyFour
    val paddingFortyFour = MaterialTheme.padding.fortyFour

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

                        val centerColumnGuideLine = createGuidelineFromTop(0.6f)

                        val (
                            locationTextField,
                            searchIndicator,
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

                        // to show the searching when user clicked the search icon
                        AnimatedVisibility(
                            modifier = Modifier.constrainAs(searchIndicator){
                                start.linkTo(parent.start, margin = paddingSixteen)
                                end.linkTo(parent.end, margin = paddingSixteen)
                                top.linkTo(locationTextField.bottom, paddingEight)
                                width = Dimension.fillToConstraints
                            },
                            visible = viewState.isSearching,
                            enter = fadeIn(animationSpec = tween(1000)),
                            exit = fadeOut(animationSpec = tween(1000)),
                        ) {
                            // This block will be shown when visible
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(paddingSixteen),
                                text = stringResource(R.string.searching),
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        }

                        // to show the list of query result or individual location weather conditions
                        if (viewState.hasQueryResult) {
                            LazyColumn(
                                modifier = Modifier.constrainAs(locationList) {
                                    start.linkTo(parent.start, margin = paddingSixteen)
                                    end.linkTo(parent.end, margin = paddingSixteen)
                                    top.linkTo(
                                        locationTextField.bottom,
                                        margin = paddingTwentyFour
                                    )
                                    bottom.linkTo(parent.bottom, margin = paddingTwentyFour)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                },
                                verticalArrangement = Arrangement.spacedBy(paddingSixteen),
                                contentPadding = PaddingValues(vertical = paddingSixteen),
                            ) {
                                items(viewState.searchLocationList) { searchModel ->
                                    SearchLocationItem(
                                        city = searchModel.name,
                                        temperature = "${searchModel.tempC.roundToInt()}°",
                                        iconUrl = searchModel.iconUrl,
                                        condition = searchModel.condition,
                                        onClicked = onSearchItemClicked,
                                    )
                                }
                            }
                        }else {

                            // to show no location text or weather info
                            if (viewState.hasPreviousLocation) {
                                // weather condition card
                                WeatherConditionCard(
                                    modifier = Modifier.constrainAs(weatherConditionCard) {
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        top.linkTo(
                                            locationTextField.bottom,
                                            margin = paddingTwentyFour
                                        )
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
                                // weather detail card
                                WeatherDetailsCard(
                                    modifier = Modifier.constrainAs(weatherInfoCard) {
                                        start.linkTo(parent.start, margin = paddingFortyFour)
                                        end.linkTo(parent.end, margin = paddingFortyFour)
                                        top.linkTo(
                                            weatherConditionCard.bottom,
                                            margin = paddingTwentyFour
                                        )
                                        width = Dimension.fillToConstraints
                                    },
                                    humidityValue = viewState.humidity.toString(),
                                    uvValue = viewState.vu.toString(),
                                    feelsLikeValue = viewState.feelsLikeC.toString(),
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
                                        top.linkTo(
                                            noLocationTextTitle.bottom,
                                            margin = paddingSixteen
                                        )
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
                description = "Sunny",
                vu = 38.39,
                lastUpdated = "autem",
            ),
            locationNameValue = "",
            onLocationNameValueChange = {},
            onSearchIconClicked = {},
            onSearchItemClicked = {},
        )
    }
}