package com.htueko.tenki.core.data.datasource.local.implementation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.htueko.tenki.core.data.datasource.local.abstraction.DataStoreDataSource
import com.htueko.tenki.core.util.getClassName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class DataStoreDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : DataStoreDataSource {

    private val tag = getClassName<DataStoreDataSourceImpl>()

    object PreferencesKeys {
        val LOCATION_NAME_KEY = stringPreferencesKey("location_name")
    }

    override suspend fun setLocationName(locationName: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LOCATION_NAME_KEY] = locationName
        }
    }

    override fun getLocationName(): Flow<String> =
        dataStore.data
            .catch { exception ->
                Timber.e(exception.message.toString())
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[PreferencesKeys.LOCATION_NAME_KEY] ?: ""
            }
}