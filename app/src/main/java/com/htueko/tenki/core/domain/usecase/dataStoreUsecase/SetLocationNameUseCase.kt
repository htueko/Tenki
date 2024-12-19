package com.htueko.tenki.core.domain.usecase.dataStoreUsecase

import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.status.ResultOf

/**
 * An interface that defines a use case for setting the location name.
 *
 * @param locationName the new location name to be set.
 */
interface SetLocationNameUseCase {
    suspend operator fun invoke(
        locationName: String,
    )
}