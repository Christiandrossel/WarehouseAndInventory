package com.open.warehouseandinventory.service

import android.util.Log
import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.repository.ProductRepository

/**
 * This singleton class is responsible for storing and retrieving the products of the application
 */
class ProductService private constructor(){

    private val productFacadeService = ProductFacadeService()
    private val productRepository = ProductRepository.instance

    companion object {
        val instance: ProductService by lazy { ProductService() }
    }

    fun getProduct(barcode: String): Product {
        return productRepository.getProductByBarcode(barcode)
            .also { Log.d("Product", "Product already exists and is being issued!") }
            ?: productFacadeService.getProduct(barcode)?.let {
                Log.d("Product", "Product was retrieved via API and is being saved!")
                productRepository.save(it)
                it
            }
            ?: Product(barcode = barcode).also {
                Log.d("Product", "A new product has been created.")
                productRepository.save(it)
            }
    }

    fun getAllProducts(): List<Product> {
        return productRepository.getAll()
    }

    fun saveProduct(product: Product) {
        productRepository.save(product)
    }

    fun deleteProduct(product: Product) {
        productRepository.delete(product)
    }
}