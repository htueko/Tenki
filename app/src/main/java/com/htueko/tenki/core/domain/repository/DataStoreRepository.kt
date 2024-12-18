package com.htueko.tenki.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setLocationName(locationName: String)
    fun getLocationName(): Flow<String>
}