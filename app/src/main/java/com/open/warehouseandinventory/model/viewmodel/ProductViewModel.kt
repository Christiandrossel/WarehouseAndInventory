package com.open.warehouseandinventory.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.open.warehouseandinventory.model.Product

class ProductViewModel: ViewModel() {
     val _product = MutableLiveData<Product>()
     val allProducts = MutableLiveData<Set<Product>>()

    val product: LiveData<Product>
        get() = _product

    val products: LiveData<Set<Product>>
        get() = allProducts


    var barcode: MutableLiveData<String>? = MutableLiveData<String>()
        get() = _product.value?.barcode?.let { field }
        set(value) {
            if (!value?.value.isNullOrEmpty())
                _product.value = product.value?.copy(barcode = value?.value)
            field = value
        }
    var name: MutableLiveData<String>? = MutableLiveData<String>()
        get() = _product.value?.name?.let { field }
        set(value) {
            if (!value?.value.isNullOrEmpty())
                _product.value = product.value?.copy(name = value?.value)
            field = value
        }
    var quantity = MutableLiveData<String>()
        get() = _product.value?.quantity.toString().let { field }
        set(value) {
            if (!value.value.isNullOrEmpty())
                _product.value =
                    product.value?.copy(quantity = value.value?.let { Integer.parseInt(it) } ?: 0)
            field = value
        }
    var description: MutableLiveData<String>? = MutableLiveData<String>()
        get() = _product.value?.description?.let { field }
        set(value) {
            if (!value?.value.isNullOrEmpty())
                _product.value = product.value?.copy(description = value?.value)
            field = value
        }


    fun setProduct(newProduct: Product) {
        _product.value = newProduct
        if (allProducts.value == null) {
            allProducts.value = setOf(newProduct)
        }
        else {
            allProducts.value?.plus(product)
        }
    }

    fun updateProductList() {
        allProducts.value?.plus(product.value)
    }

    fun setAllProducts(products: List<Product>) {
        allProducts.value = products.toSet()
    }
}