package com.open.warehouseandinventory.api

import com.open.warehouseandinventory.model.BarcodeLookupResponse
import com.open.warehouseandinventory.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service interface for the Barcode Lookup API.
 * This interface defines the endpoint to retrieve product information based on a barcode.
 * It uses Retrofit for network operations.
 */
interface BarCodeLookupApiService {

    @GET("v3/products")
    fun getProduct(
        @Query("barcode") barcode: String,
        @Query("formatted") formatted: String = "y",
        @Query("key") apiKey: String
    ): Call<BarcodeLookupResponse>
}