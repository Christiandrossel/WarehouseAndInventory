package com.open.warehouseandinventory.service

import com.open.warehouseandinventory.connector.BarcodeLookupConnector
import com.open.warehouseandinventory.model.Product

/**
 * This service is intended to refer to various Barcorde connectors.
 * The reason is that Bracode libraries only have a quota limit.
 * These should first be used by different libraries
 */
class ProductFacadeService {

    private val barcodeLookupConnector = BarcodeLookupConnector()

    fun getProduct(barcode: String): Product? {
        return barcodeLookupConnector.getProduct(barcode)
    }
}