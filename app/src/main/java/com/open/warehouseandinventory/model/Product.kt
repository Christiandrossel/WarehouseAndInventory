package com.open.warehouseandinventory.model

data class Product(
    val name: String?,
    val barcode: String?,
    val quantity: Int = 1,
    val description: String? = null
)