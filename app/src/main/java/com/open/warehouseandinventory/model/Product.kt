package com.open.warehouseandinventory.model

import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val barcode: String?,
    val name: String?,
    val quantity: Int = 1,
    val description: String? = null
)