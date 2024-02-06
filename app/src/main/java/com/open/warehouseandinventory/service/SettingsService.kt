package com.open.warehouseandinventory.service

import android.content.Context
import com.open.warehouseandinventory.model.Settings
import com.open.warehouseandinventory.repository.SettingsDataStoreRepository
import kotlinx.coroutines.flow.Flow

/**
 * This singleton class is responsible for storing and retrieving the settings of the application
 *
 */
class SettingsService private constructor(private val context: Context) {

    companion object {
        fun getInstance(context: Context): SettingsService {
            return SettingsService(context)
        }
    }

    private val settingsDataStoreRepository = SettingsDataStoreRepository(context)

    fun getIsTestingEnabled(): Flow<Boolean> {
        return settingsDataStoreRepository.readIsTestingEnabled()
    }

    suspend fun saveIsTestingEnabled(isTestingEnabled: Boolean) {
        settingsDataStoreRepository.saveIsTestingEnabled(isTestingEnabled)
    }
}