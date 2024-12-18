package com.htueko.tenki.feature.home.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.htueko.tenki.feature.home.event.HomeEvent
import com.htueko.tenki.feature.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    HomeContent(
        viewState = viewState,
        locationNameValue = viewModel.locationNameText,
        onLocationNameValueChange = viewModel::onLocationNameValueChange,
        onSearchIconClicked = {
            viewModel.sendUiEvent(HomeEvent.OnSearchIconClicked)
        },
        onSearchItemClicked = {locationName ->
            viewModel.sendUiEvent(HomeEvent.GetCurrentWeatherByLocation(locationName))
        }
    )

}