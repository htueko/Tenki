package com.htueko.tenki.core.domain.usecase.dataStoreUsecase

import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.status.ResultOf

interface SetLocationNameUseCase {
    suspend operator fun invoke(
        locationName: String,
    )
}