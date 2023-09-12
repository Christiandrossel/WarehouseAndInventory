package com.open.warehouseandinventory.api

import com.open.warehouseandinventory.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface ScanBotApiService {

    @GET("products/{code}")
    fun getProduct(@Path("code") code: String): Call<Product>
}