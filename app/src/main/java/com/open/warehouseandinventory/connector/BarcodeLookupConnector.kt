package com.open.warehouseandinventory.connector

import com.open.warehouseandinventory.BuildConfig
import com.open.warehouseandinventory.api.BarCodeLookupApiService
import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.model.mapper.toProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BarcodeLookupConnector(): ProductConnector {

    private val url = "https://api.barcodelookup.com/"
    private val retroFit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiKey = BuildConfig.BARCODELOOKUP_API_KEY

    override suspend fun getProduct(barcode: String): Product? {
        // call the API with barcode
        val productApiService = retroFit.create(BarCodeLookupApiService::class.java)
        val call = productApiService.getProduct(barcode, "y", apiKey)
        return try {
            withContext(Dispatchers.IO) { // Execute network call on IO dispatcher
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()?.toProduct()
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace() // Consider more robust error handling
            null
        }
    }
}
