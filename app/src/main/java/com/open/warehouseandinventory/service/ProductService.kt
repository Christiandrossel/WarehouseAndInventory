package com.open.warehouseandinventory.service

import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.model.viewmodel.ProductViewModel
import com.open.warehouseandinventory.repository.ProductRepository

class ProductService {

    private val productFacadeService = ProductFacadeService()
    private val productRepository = ProductRepository.getInstance()
    private val productViewModel = ProductViewModel()

    fun getProduct(barcode: String): ProductViewModel {
        val product = productRepository.getProductByBarcode(barcode)
            ?: productFacadeService.getProduct(barcode)
        if (product != null) {
            productRepository.save(product)
            productViewModel.setProduct(product)
        }
        return productViewModel
    }

    fun getAllProducts(): ProductViewModel {
        val products = productRepository.getAll()
        productViewModel.setAllProducts(products)
        return productViewModel
    }

    fun saveProduct(product: Product) {
        productRepository.save(product)
    }
}