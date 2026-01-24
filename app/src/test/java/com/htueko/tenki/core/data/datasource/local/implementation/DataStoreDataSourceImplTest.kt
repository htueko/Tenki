package com.htueko.tenki.core.data.datasource.local.implementation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.htueko.tenki.core.data.datasource.local.abstraction.DataStoreDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class DataStoreDataSourceTest {

    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var dataSource: DataStoreDataSource

    @Before
    fun setUp() {
        dataStore = mockk(relaxed = true)
        dataSource = DataStoreDataSourceImpl(dataStore)
    }

    @Test
    fun `getLocationName emits empty string when DataStore throws IOException`() = runTest {
        every { dataStore.data } returns flow { throw IOException("Error reading data") }
        val flow = dataSource.getLocationName()
        val values = flow.toList()

        assert(values.size == 1)
        assert(values[0] == "")
    }

    @Test
    fun `getLocationName emits saved location name`() = runTest {
        val locationName = "London, UK"
        val preferences = mockk<Preferences>()
        every { preferences[DataStoreDataSourceImpl.PreferencesKeys.LOCATION_NAME_KEY] } returns locationName
        every { dataStore.data } returns flowOf(preferences)

        val flow = dataSource.getLocationName()
        val value = flow.first()

        assert(value == locationName)
    }

    @Test
    fun `getLocationName emits empty string when no location is saved`() = runTest {
        every { dataStore.data } returns flowOf(emptyPreferences())

        val flow = dataSource.getLocationName()
        val value = flow.first()

        assert(value == "")
    }
}
