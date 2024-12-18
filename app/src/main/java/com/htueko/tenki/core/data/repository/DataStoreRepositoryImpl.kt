package com.htueko.tenki.core.data.repository

import com.htueko.tenki.core.data.datasource.local.abstraction.DataStoreDataSource
import com.htueko.tenki.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : DataStoreRepository {
    override suspend fun setLocationName(locationName: String) {
        dataStoreDataSource.setLocationName(locationName)
    }

    override fun getLocationName(): Flow<String> =
        dataStoreDataSource.getLocationName()
}