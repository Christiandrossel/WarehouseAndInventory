package com.open.warehouseandinventory.connector

import com.open.warehouseandinventory.api.DatakickApiService
import com.open.warehouseandinventory.model.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DatakickConnector: ProductConnector {

    private val url = "https://api.datakick.org/api/"
    private val retroFit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun getProduct(barcode: String): Product? {
        // call the API with barcode
        val productApiService = retroFit.create(DatakickApiService::class.java)
        val call = productApiService.getProduct(barcode)

        val response = call.execute()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}