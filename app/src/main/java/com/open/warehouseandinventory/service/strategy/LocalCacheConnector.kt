package com.open.warehouseandinventory.service.strategy

import com.open.warehouseandinventory.connector.ProductConnector
import com.open.warehouseandinventory.model.Product

class LocalCacheConnector(
    private val cache: Map<String, Product>
) : ProductConnector {
    override suspend fun getProduct(barcode: String): Product? = cache[barcode]
}
