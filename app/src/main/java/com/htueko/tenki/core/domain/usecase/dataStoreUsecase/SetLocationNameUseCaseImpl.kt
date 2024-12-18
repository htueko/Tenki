package com.htueko.tenki.core.domain.usecase.dataStoreUsecase

import com.htueko.tenki.core.domain.repository.DataStoreRepository
import javax.inject.Inject

class SetLocationNameUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): SetLocationNameUseCase{
    override suspend fun invoke(locationName: String) {
        dataStoreRepository.setLocationName(locationName)
    }
}