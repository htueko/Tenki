package com.htueko.tenki.feature.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.tenki.core.di.DefaultDispatcher
import com.htueko.tenki.core.di.IoDispatcher
import com.htueko.tenki.core.di.MainDispatcher
import com.htueko.tenki.core.domain.model.status.ResultOf
import com.htueko.tenki.core.domain.usecase.dataStoreUsecase.GetLocationNameUseCase
import com.htueko.tenki.core.domain.usecase.dataStoreUsecase.SetLocationNameUseCase
import com.htueko.tenki.core.domain.usecase.weatherUsecase.GetCurrentWeatherByLocationUseCase
import com.htueko.tenki.core.util.getClassName
import com.htueko.tenki.core.util.logError
import com.htueko.tenki.core.util.logInfo
import com.htueko.tenki.feature.home.effect.HomeEffect
import com.htueko.tenki.feature.home.event.HomeEvent
import com.htueko.tenki.feature.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher
    private val uiDispatcher: CoroutineDispatcher,
    @DefaultDispatcher
    private val defaultDispatcher: CoroutineDispatcher,
    private val getCurrentWeatherByLocationUseCase: GetCurrentWeatherByLocationUseCase,
    private val setLocationNameUseCase: SetLocationNameUseCase,
    private val getLocationNameUseCase: GetLocationNameUseCase,
) : ViewModel() {

    private val tag = getClassName<HomeViewModel>()

    private val _uiEffect = Channel<HomeEffect>()
    internal val uiEffect = _uiEffect.receiveAsFlow()

    private val _uiEvent = Channel<HomeEvent>()

    private val _viewState: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState())
    internal val viewState: StateFlow<HomeUiState> = _viewState.asStateFlow()

    var locationNameText by mutableStateOf("")
        private set

    private val exceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            logError(tag, "$coroutineContext and ${throwable.message.orEmpty()}")
        }
    private val uiContent = uiDispatcher + exceptionHandler
    private val ioContent = ioDispatcher + exceptionHandler
    private val defaultContent = defaultDispatcher + exceptionHandler

    init {
        toggleLoadingIndicator(true)
        handlePreferencesChange()
    }

    private fun handlePreferencesChange() {
        viewModelScope.launch(ioContent) {
            getLocationNameUseCase()
                .catch { throwable ->
                    withContext(uiContent) {
                        _viewState.update {
                            it.copy(
                                hasPreviousLocation = false,
                            )
                        }
                    }
                    getCurrentWeather("London")
                }
                .collectLatest { locationName ->
                    withContext(uiContent) {
                        _viewState.update {
                            it.copy(
                                hasPreviousLocation = true,
                            )
                        }
                    }
                    getCurrentWeather(locationName)
                }
        }
    }

    private fun getCurrentWeather(locationName: String) {
        viewModelScope.launch(ioContent) {
            when (val response = getCurrentWeatherByLocationUseCase("London")) {
                is ResultOf.ApiError -> {
                    logError(tag, "getCurrentWeather api error: ${response.message}")
                    withContext(uiContent) {
                        _viewState.update {
                            it.copy(
                                description = response.message
                            )
                        }
                    }
                    toggleLoadingIndicator(false)
                }

                is ResultOf.NetworkError -> {
                    logError(tag, "getCurrentWeather network error: ${response.throwable.message}")
                    withContext(uiContent) {
                        _viewState.update {
                            it.copy(
                                description = if (response.throwable.message?.isBlank() == true) "is blank network error" else response.throwable.message.orEmpty()
                            )
                        }
                    }
                    toggleLoadingIndicator(false)
                }

                is ResultOf.Success -> {
                    logInfo(tag, "getCurrentWeather success: ${response.data}")
                    withContext(uiContent) {
                        _viewState.update {
                            it.copy(
                                name = response.data.name,
                                tempC = response.data.tempC,
                                tempF = response.data.tempF,
                                feelsLikeC = response.data.feelsLikeC,
                                feelsLikeF = response.data.feelsLikeF,
                                icon = response.data.icon,
                                humidity = response.data.humidity,
                                vu = response.data.vu,
                                lastUpdated = response.data.lastUpdated,
                                description = response.data.description,
                                windDirection = response.data.windDegree,
                            )
                        }
                    }
                    toggleLoadingIndicator(false)
                }
            }
        }
    }

    private fun toggleLoadingIndicator(value: Boolean) {
        viewModelScope.launch(uiContent) {
            _viewState.update {
                it.copy(isLoading = value)
            }
        }
    }

    private fun sendUiEffect(effect: HomeEffect) {
        viewModelScope.launch(uiContent) {
            logInfo(tag, "sendUiEffect effect is $effect")
            _uiEffect.send(effect)
        }
    }

    private fun sendUiEvent(event: HomeEvent) {
        viewModelScope.launch(uiContent) {
            logInfo(tag, "sendUiEvent event is $event")
            _uiEvent.send(event)
        }
    }

}