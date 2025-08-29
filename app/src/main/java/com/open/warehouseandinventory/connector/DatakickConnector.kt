package com.open.warehouseandinventory.connector

import com.open.warehouseandinventory.api.DatakickApiService
import com.open.warehouseandinventory.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DatakickConnector: ProductConnector {

    private val url = "https://api.datakick.org/api/"
    private val retroFit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override suspend fun getProduct(barcode: String): Product? {
        // call the API with barcode
        val productApiService = retroFit.create(DatakickApiService::class.java)
        val call = productApiService.getProduct(barcode)

        return try {
            withContext(Dispatchers.IO) { // Execute network call on IO dispatcher
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    // Consider more robust error handling, e.g., logging or specific exceptions
                    null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace() // Consider more robust error handling
            null
        }
    }
}