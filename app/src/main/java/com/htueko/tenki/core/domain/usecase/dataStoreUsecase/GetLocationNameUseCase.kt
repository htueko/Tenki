package com.htueko.tenki.core.domain.usecase.dataStoreUsecase

import kotlinx.coroutines.flow.Flow

/**
 * An interface that defines a use case for retrieving the user's current location name.
 * The `invoke()` function is a suspend function that returns a [Flow] of the location name as a [String].
 */
interface GetLocationNameUseCase {
    suspend operator fun invoke(): Flow<String>
}