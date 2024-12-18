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

/**
 * Implements the [DataStoreDataSource] interface to provide a local data source for storing and retrieving location name data using the DataStore API.
 *
 * @property dataStore the DataStore instance used to store and retrieve preferences
 */
class DataStoreDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : DataStoreDataSource {

    private val tag = getClassName<DataStoreDataSourceImpl>()

    object PreferencesKeys {
        val LOCATION_NAME_KEY = stringPreferencesKey("location_name")
    }

    /**
     * Sets the location name in the DataStore preferences.
     *
     * @param locationName the new location name to be stored
     */
    override suspend fun setLocationName(locationName: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LOCATION_NAME_KEY] = locationName
        }
    }

    /**
     * Retrieves the location name stored in the DataStore preferences.
     *
     * This method uses the DataStore API to fetch the location name from the preferences. If an IOException is encountered while reading the data, it will emit an empty set of preferences. Any other exceptions will be rethrown.
     *
     * @return a [Flow] of the location name stored in the preferences, or an empty string if the location name is not found.
     */
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