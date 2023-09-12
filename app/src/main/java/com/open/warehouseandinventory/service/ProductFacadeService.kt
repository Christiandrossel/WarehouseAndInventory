package com.open.warehouseandinventory.service

import com.open.warehouseandinventory.connector.BarcodeLookupConnector
import com.open.warehouseandinventory.model.Product

class ProductFacadeService {

    private val barcodeLookupConnector = BarcodeLookupConnector()

    fun getProduct(barcode: String): Product? {
        return barcodeLookupConnector.getProduct(barcode)
    }
}