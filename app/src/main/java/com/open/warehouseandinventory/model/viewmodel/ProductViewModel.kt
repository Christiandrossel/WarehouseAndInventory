package com.open.warehouseandinventory.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.open.warehouseandinventory.model.Product

class ProductViewModel: ViewModel() {
    private var currentProduct = MutableLiveData<Product>()
    private val allProducts = MutableLiveData<Set<Product>>()
    val products: LiveData<Set<Product>>
        get() = allProducts

    val product: LiveData<Product>
        get() = currentProduct

    var barcode: MutableLiveData<String>? = MutableLiveData<String>()
        get() = currentProduct.value?.barcode?.let { field }
        set(value) {
            if (!value?.value.isNullOrEmpty())
                currentProduct.value = currentProduct.value?.copy(barcode = value?.value)
            field = value
        }
    var name: MutableLiveData<String>? = MutableLiveData<String>()
        get() = currentProduct.value?.name?.let { field }
        set(value) {
            if (!value?.value.isNullOrEmpty())
                currentProduct.value = currentProduct.value?.copy(name = value?.value)
            field = value
        }
    var quantity = MutableLiveData<String>()
        get() = currentProduct.value?.quantity.toString().let { field }
        set(value) {
            if (!value.value.isNullOrEmpty())
                currentProduct.value =
                    currentProduct.value?.copy(quantity = value.value?.let { Integer.parseInt(it) } ?: 0)
            field = value
        }
    var description: MutableLiveData<String>? = MutableLiveData<String>()
        get() = currentProduct.value?.description?.let { field }
        set(value) {
            if (!value?.value.isNullOrEmpty())
                currentProduct.value = currentProduct.value?.copy(description = value?.value)
            field = value
        }


    fun setProduct(product: Product) {
        currentProduct.value = product
        if (allProducts.value == null) {
            allProducts.value = setOf(product)
        }
        else {
            allProducts.value?.plus(product)
        }
    }

    fun updateProductList() {
        allProducts.value?.plus(currentProduct.value)
    }

    fun setAllProducts(products: List<Product>) {
        allProducts.value = products.toSet()
    }
}