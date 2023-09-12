package com.open.warehouseandinventory.api

import com.open.warehouseandinventory.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GtinSearchApiService {

    @GET("items/{gtin}")
    fun getProduct(@Path("gtin") gtin: String): Call<Product>
}