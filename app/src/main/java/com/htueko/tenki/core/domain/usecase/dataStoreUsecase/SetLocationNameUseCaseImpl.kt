package com.htueko.tenki.core.domain.usecase.dataStoreUsecase

import com.htueko.tenki.core.domain.repository.DataStoreRepository
import javax.inject.Inject

/**
 * An implementation of the [SetLocationNameUseCase] interface that sets the location name in the data store repository.
 *
 * @param dataStoreRepository The repository responsible for managing the data store.
 */
class SetLocationNameUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): SetLocationNameUseCase{
    override suspend fun invoke(locationName: String) {
        dataStoreRepository.setLocationName(locationName)
    }
}