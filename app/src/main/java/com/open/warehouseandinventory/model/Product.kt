package com.open.warehouseandinventory.model

data class Product(
    val id: String,
    val barcode: String?,
    val name: String?,
    val quantity: Int = 1,
    val description: String? = null
)