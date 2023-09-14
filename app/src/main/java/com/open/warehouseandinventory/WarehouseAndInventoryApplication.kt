package com.open.warehouseandinventory

import android.app.Application
import io.paperdb.Paper

class WarehouseAndInventoryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instanciateDatabase(this)

    }

    private fun instanciateDatabase(application: Application) {
        Paper.init(application)
    }
}