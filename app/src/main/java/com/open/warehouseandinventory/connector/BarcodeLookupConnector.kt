package com.open.warehouseandinventory.connector

import com.open.warehouseandinventory.api.BarCodeLookupApiService
import com.open.warehouseandinventory.api.ScanBotApiService
import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.model.mapper.toProduct
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BarcodeLookupConnector: ProductConnector {

    private val url = "https://api.barcodelookup.com/v3/products/"
    private val retroFit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiKey = "2qmcsw8yo98iia54pgqxpl5z69wy57"

    override fun getProduct(barcode: String): Product? {
        // call the API with barcode
        val productApiService = retroFit.create(BarCodeLookupApiService::class.java)
        val call = productApiService.getProduct(barcode, "y", apiKey)

        val response = call.execute()
        return if (response.isSuccessful) {
            response.body()?.toProduct()
        } else {
            null
        }
    }
}