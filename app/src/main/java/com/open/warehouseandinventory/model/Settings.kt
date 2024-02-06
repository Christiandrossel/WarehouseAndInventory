package com.open.warehouseandinventory.model

data class Settings(
    val isTestingEnabled: Boolean = false
) {
    companion object {
        fun getDefaultInstance(): Settings {
            return Settings(isTestingEnabled = false)
        }
    }
}
