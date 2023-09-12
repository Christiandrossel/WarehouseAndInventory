package com.open.warehouseandinventory.connector

import com.open.warehouseandinventory.model.Product

interface ProductConnector {

    fun getProduct(barcode: String): Product?
}