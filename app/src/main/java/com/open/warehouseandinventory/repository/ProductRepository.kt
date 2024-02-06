package com.open.warehouseandinventory.repository

import com.open.warehouseandinventory.model.Product
import io.paperdb.Paper

/**
 * This singleton class is responsible for storing and retrieving the products of the application
 */
class ProductRepository  private constructor() {
    companion object {
        val instance: ProductRepository by lazy { ProductRepository() }
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

    fun delete (product: Product) {
        Paper.book().delete(product.id)
    }
}