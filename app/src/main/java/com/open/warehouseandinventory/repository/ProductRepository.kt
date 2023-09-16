package com.open.warehouseandinventory.repository

import com.open.warehouseandinventory.model.Product
import io.paperdb.Paper

class ProductRepository  private constructor() {
    companion object {
        @Volatile
        private var repository: ProductRepository? = null

        fun getInstance(): ProductRepository {
            return repository ?: synchronized(this) {
                repository ?: ProductRepository().also { repository = it }
            }
        }
    }

    fun save (product: Product) {
        Paper.book().write(product.id, product)
    }

    fun get (id: String): Product? {
        return Paper.book().read(id)
    }

    fun getProductByBarcode (barcode: String): Product? {
        val products: List<Product> = Paper.book().allKeys.mapNotNull { Paper.book().read(it) }
        return products.find { it.barcode == barcode }
    }

    fun getAll (): List<Product> {
        return Paper.book().allKeys.mapNotNull { Paper.book().read(it) }
    }
}