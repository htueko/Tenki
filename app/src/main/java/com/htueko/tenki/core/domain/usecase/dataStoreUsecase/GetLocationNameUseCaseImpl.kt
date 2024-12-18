package com.htueko.tenki.core.domain.usecase.dataStoreUsecase

import com.htueko.tenki.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implements the [GetLocationNameUseCase] interface to retrieve the location name from the [DataStoreRepository].
 *
 * @property dataStoreRepository the repository that manages the location name data.
 */
class GetLocationNameUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : GetLocationNameUseCase {
    override suspend fun invoke(): Flow<String> =
        dataStoreRepository.getLocationName()
}