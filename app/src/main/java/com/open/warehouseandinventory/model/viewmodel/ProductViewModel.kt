package com.open.warehouseandinventory.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.open.warehouseandinventory.model.Product

class ProductViewModel: ViewModel() {
     val _product = MutableLiveData<Product>()
     val _allProducts = MutableLiveData<Set<Product>>()

    val product: LiveData<Product>
        get() = _product

    val products: LiveData<Set<Product>>
        get() = _allProducts


    var barcode: MutableLiveData<String?>? = MutableLiveData<String?>()
        get() = _product.value?.barcode?.let { field }

    var name: MutableLiveData<String?>? = MutableLiveData<String?>()
        get() = _product.value?.name?.let { field }

    var quantity = MutableLiveData<String>()
        get() = _product.value?.quantity.toString().let { field }

    var description: MutableLiveData<String?>? = MutableLiveData<String?>()
        get() = _product.value?.description?.let { field }

    fun updateBarcode(newBarcode: String) {
        _product.value = product.value?.copy(barcode = newBarcode)
        barcode?.value = newBarcode
    }

    fun updateName(newName: String) {
        _product.value = product.value?.copy(name = newName)
        name?.value = newName
    }

    fun updateQuantity(newQuantity: String) {
        _product.value = product.value?.copy(quantity = Integer.parseInt(newQuantity))
        quantity.value = newQuantity
    }

    fun updateDescription(newDescription: String) {
        _product.value = product.value?.copy(description = newDescription)
        description?.value = newDescription
    }


    fun setProduct(newProduct: Product) {
        _product.value = newProduct
        if (_allProducts.value == null) {
            _allProducts.value = setOf(newProduct)
        }
        else {
            _allProducts.value?.plus(product)
            // set value to barcode
            barcode?.value = newProduct.barcode
            name?.value = newProduct.name
            quantity.value = newProduct.quantity.toString()
            description?.value = newProduct.description
        }
    }

    fun updateProduct(
        name: String,
        barcode: String,
        quantity: Int,
        description: String
    ): Product {
        _product.value = product.value?.copy(
            name = name,
            barcode = barcode,
            quantity = quantity,
            description = description
        )
        updateBarcode(barcode)
        updateName(name)
        updateQuantity(quantity.toString())
        updateDescription(description)
        return _product.value!!
    }

    /**
     * updates the list of products.
     * If the product already exists in the list it is updated.
     * If the product does not exist in the list it is added.
     */
    fun updateProductList(updatedProduct: Product) {       //TODO Speichert product nicht in die productliste
        val currentProducts = _allProducts.value ?: emptySet()
        val updatedProducts = currentProducts.map {
            if (it.id == updatedProduct.id) {
                updatedProduct
            } else {
                it
            }
        }.toSet()

        _allProducts.value = updatedProducts
    }


    fun setAllProducts(products: List<Product>) {
        _allProducts.value = products.toSet()
    }
}