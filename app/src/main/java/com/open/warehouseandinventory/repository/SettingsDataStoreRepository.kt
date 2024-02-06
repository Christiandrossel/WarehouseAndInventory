package com.open.warehouseandinventory.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.open.warehouseandinventory.model.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * This class is responsible for storing and retrieving the settings of the application
 * This class must be instantiated with the application context n the top level of the kotlin file (main activity)
 *
 * https://medium.com/@codingin254/kickstart-with-datastore-jetpack-data-storage-library-in-2023-f1e15baacfdf
 */
class SettingsDataStoreRepository(context: Context) {
    private object PreferencesKey {
        val isTestingEnabled = booleanPreferencesKey(name = Constant.IS_TESTING_ENABLED)
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constant.SETTINGS)
    private val dataStore: DataStore<Preferences> = context.dataStore


    suspend fun saveIsTestingEnabled(isTestingEnabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.isTestingEnabled] = isTestingEnabled
        }
    }

    fun readIsTestingEnabled(): Flow<Boolean> {
        return dataStore.data
            .map { preferences ->
                preferences[PreferencesKey.isTestingEnabled] ?: false
            }
    }
}