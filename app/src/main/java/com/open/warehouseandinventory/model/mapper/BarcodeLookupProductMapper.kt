package com.open.warehouseandinventory.model.mapper

import com.open.warehouseandinventory.model.BarcodeLookupResponse
import com.open.warehouseandinventory.model.Product
import java.util.*

fun BarcodeLookupResponse.toProduct(): Product {
    return Product(
        id = UUID.randomUUID().toString(),
        barcode = this.barcodeNumber,
        name = this.title,
        quantity = 1,
        description = this.description
    )
}