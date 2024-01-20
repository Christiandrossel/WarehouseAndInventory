package com.open.warehouseandinventory.model

import androidx.lifecycle.MutableLiveData
import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val barcode: String? = null,
    val name: String? = null,
    val quantity: Int = 1,
    val description: String? = null
)

data class ProductLiveData(
    val id: String = UUID.randomUUID().toString(),
    val barcode: MutableLiveData<String>,
    val name: MutableLiveData<String>,
    val quantity: MutableLiveData<String>,
    val description: MutableLiveData<String>
)