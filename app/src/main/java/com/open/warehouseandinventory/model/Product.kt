package com.open.warehouseandinventory.model

data class Product(
    val name: String,
    val barcode: String,
    val price: Double,
    val quantity: Int = 1,
    val description: String? = null
)