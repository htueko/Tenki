package com.htueko.tenki.core.data.repository

import com.htueko.tenki.core.data.datasource.local.abstraction.DataStoreDataSource
import com.htueko.tenki.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implements the [DataStoreRepository] interface, providing functionality to
 * store and retrieve the user's location name.
 *
 * @property dataStoreDataSource the data source for storing and retrieving
 * the location name
 */
class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : DataStoreRepository {
    /**
     * Sets the user's location name in the data store.
     *
     * @param locationName the new location name to be stored
     */
    override suspend fun setLocationName(locationName: String) {
        dataStoreDataSource.setLocationName(locationName)
    }

    /**
     * Gets the user's location name from the data store as a [Flow] of [String].
     *
     * @return a [Flow] that emits the user's location name
     */
    override fun getLocationName(): Flow<String> =
        dataStoreDataSource.getLocationName()
}