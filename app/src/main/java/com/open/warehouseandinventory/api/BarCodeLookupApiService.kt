package com.open.warehouseandinventory.api

import com.open.warehouseandinventory.model.BarcodeLookupResponse
import com.open.warehouseandinventory.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BarCodeLookupApiService {

    @GET("products")
    fun getProduct(
        @Query("barcode") barcode: String,
        @Query("formatted") formatted: String,
        @Query("key") apiKey: String
    ): Call<BarcodeLookupResponse>
}