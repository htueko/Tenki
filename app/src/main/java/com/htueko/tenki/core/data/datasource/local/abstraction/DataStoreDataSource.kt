package com.htueko.tenki.core.data.datasource.local.abstraction

import kotlinx.coroutines.flow.Flow

interface DataStoreDataSource {

    suspend fun setLocationName(locationName: String)
    fun getLocationName(): Flow<String>

}