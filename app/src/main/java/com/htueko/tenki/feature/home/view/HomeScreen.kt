package com.htueko.tenki.feature.home.view

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.htueko.tenki.feature.home.effect.HomeEffect
import com.htueko.tenki.feature.home.event.HomeEvent
import com.htueko.tenki.feature.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is HomeEffect.ShowMessage -> {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    HomeContent(
        viewState = viewState,
        locationNameValue = viewModel.locationNameText,
        onLocationNameValueChange = viewModel::onLocationNameValueChange,
        onSearchIconClicked = {
            viewModel.sendUiEvent(HomeEvent.OnSearchIconClicked)
        },
        onSearchItemClicked = { locationName ->
            viewModel.sendUiEvent(HomeEvent.GetCurrentWeatherByLocation(locationName))
        }
    )

}