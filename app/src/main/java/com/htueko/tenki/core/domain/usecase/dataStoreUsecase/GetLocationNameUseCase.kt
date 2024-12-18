package com.htueko.tenki.core.domain.usecase.dataStoreUsecase

import kotlinx.coroutines.flow.Flow

interface GetLocationNameUseCase {
    suspend operator fun invoke(): Flow<String>
}