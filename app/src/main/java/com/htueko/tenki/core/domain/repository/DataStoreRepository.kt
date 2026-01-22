package com.htueko.tenki.core.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing data store operations, such as storing and retrieving the user's location name.
 */
interface DataStoreRepository {
    /**
     * Stores the user's location name in the data store.
     *
     * @param locationName The location name to be stored.
     */
    suspend fun setLocationName(locationName: String)
    /**
     * Retrieves the user's location name from the data store.
     *
     * @return A [Flow] of the user's location name.
     */
    fun getLocationName(): Flow<String>
}