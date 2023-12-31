package com.open.warehouseandinventory.service

import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.repository.ProductRepository

class ProductService {

    private val productFacadeService = ProductFacadeService()
    private val productRepository = ProductRepository.getInstance()

    fun getProduct(barcode: String): Product? {
        return productRepository.getProductByBarcode(barcode)
            ?: productFacadeService.getProduct(barcode)?.let {
                productRepository.save(it)
                it
            }
    }

    fun getAllProducts(): List<Product> {
        return productRepository.getAll()
    }

    fun saveProduct(product: Product) {
        productRepository.save(product)
    }

    fun addTestData() {
        val product1 =
            Product(id = "111", barcode = "1234", name = "Product 1", quantity = 10, description = "This is product 1")
        val product2 =
            Product(id = "222", barcode = "1235", name = "Product 2", quantity = 20, description = "This is product 2")
        val product3 =
            Product(id = "333", barcode = "1236", name = "Product 3", quantity = 30, description = "This is product 3")
        val product4 =
            Product(id = "444", barcode = "1237", name = "Product 4", quantity = 40, description = "This is product 4")
        val product5 =
            Product(id = "555", barcode = "1238", name = "Product 5", quantity = 50, description = "This is product 5")
        val productlist = listOf(product1, product2, product3, product4, product5)
        productlist.forEach { productRepository.save(it) }
    }
}