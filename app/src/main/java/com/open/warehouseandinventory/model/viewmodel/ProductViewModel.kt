package com.open.warehouseandinventory.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.open.warehouseandinventory.model.Product

class ProductViewModel: ViewModel() {
    private val _allProducts = MutableLiveData<MutableList<Product>>()
    private val _product = MutableLiveData<Product>()

    /**
     * This is the current product that is displayed in the form view and can be edited
     */
    val product: LiveData<Product>
        get() = _product

    /**
     * The list of all products that are stored in the database and displayed in the list view
     */
    val products: LiveData<List<Product>>
        get() = _allProducts.value?.toMutableList()?.let { MutableLiveData(it) } ?: MutableLiveData()

    /**
     * the barcode from the current product
     */
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


    fun getProduct(position: Int): Product? {
        return _allProducts.value?.get(position)
    }

    /**
     * Set a product in the product list and as current product
     * Put all parameters of the product into the corresponding LiveData
     */
    fun setProduct(newProduct: Product) {
        _product.value = newProduct
        if (_allProducts.value == null) {
            _allProducts.value = mutableListOf(newProduct)
        }
        else {
            // set value from product to LiveData
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

//        updateProductList()
        return _product.value!!
    }

    /**
     * updates the list of products.
     * If the product already exists in the list it is updated.
     * If the product does not exist in the list it is added.
     */
    fun updateProducts(updatedProduct: Product) {
        _allProducts.value?.let {
            val index = it.indexOfFirst { product -> product.id == updatedProduct.id }
            if (index >= 0) {
                it[index] = updatedProduct
            }
            else {
                it.add(updatedProduct)
            }
        }
    }

    fun addAllProducts(products: List<Product>) {
        _allProducts.value = products.toMutableList()
    }

    fun removeAt(position: Int) {
        _allProducts.value?.removeAt(position)
    }

    fun addProductAt(position: Int, productToDelete: Product) {
        _allProducts.value?.add(position, productToDelete)
    }

    fun isNotEmpty(): Boolean {
        return name?.value?.isNotEmpty() == true
                && quantity.value?.isNotEmpty() == true
                && quantity.value?.toInt()!! > 0
    }

    fun isQuantityEmpty() : Boolean {
        return quantity.value?.isEmpty() == true
    }
    fun isQuantityLowerThenZero() : Boolean {
        return quantity.value?.toInt()!! < 0
    }
}