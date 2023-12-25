package com.open.warehouseandinventory

class ProductCanNotDeleteException(private val position: Int, val exception: Exception? = null) : Exception() {
    override val message: String
        get() = "Product can not be deleted at position $position"
}