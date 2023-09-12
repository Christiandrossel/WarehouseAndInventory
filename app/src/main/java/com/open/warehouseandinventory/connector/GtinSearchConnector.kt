package com.open.warehouseandinventory.connector

import com.open.warehouseandinventory.api.GtinSearchApiService
import com.open.warehouseandinventory.model.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GtinSearchConnector: ProductConnector {

    private val url = "https://www.gtinsearch.org/api/"
    private val retroFit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun getProduct(barcode: String): Product? {
        // call the API with barcode
        val productApiService = retroFit.create(GtinSearchApiService::class.java)
        val call = productApiService.getProduct(barcode)

        val response = call.execute()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}