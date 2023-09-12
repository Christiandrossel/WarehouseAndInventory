package com.open.warehouseandinventory.model.mapper

import com.open.warehouseandinventory.model.BarcodeLookupResponse
import com.open.warehouseandinventory.model.Product

fun BarcodeLookupResponse.toProduct(): Product {
    return Product(
        name = this.title,
        barcode = this.barcodeNumber,
        quantity = 1,
        description = this.description
    )
}