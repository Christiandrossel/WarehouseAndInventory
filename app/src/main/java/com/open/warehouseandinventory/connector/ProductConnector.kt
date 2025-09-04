package com.open.warehouseandinventory.connector

import com.open.warehouseandinventory.model.Product

interface ProductConnector {

    suspend fun getProduct(barcode: String): Product?
}



