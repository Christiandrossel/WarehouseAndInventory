package com.open.warehouseandinventory.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.open.warehouseandinventory.model.Product

class ProductViewModel: ViewModel() {
    private val currentProduct = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = currentProduct

    private val allProducts = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = allProducts

    fun setProduct(product: Product) {
        currentProduct.value = product
        if (allProducts.value == null) {
            allProducts.value = listOf(product)
        }
        else {
            allProducts.value?.plus(product)
        }
    }

    fun setAllProducts(products: List<Product>) {
        allProducts.value = products
    }
}