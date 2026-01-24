package com.htueko.tenki.core.data.datasource.local.abstraction

import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the data source for managing location name data in the local data store.
 */
interface DataStoreDataSource {

    /**
     * Sets the location name in the local data store.
     *
     * @param locationName The new location name to be stored.
     */
    suspend fun setLocationName(locationName: String)
    /**
     * Gets the location name stored in the local data store.
     *
     * @return A [Flow] that emits the current location name.
     */
    fun getLocationName(): Flow<String>

}