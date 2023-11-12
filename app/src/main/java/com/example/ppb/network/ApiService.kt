package com.example.ppb.network

import com.example.ppb.model.ProductList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    fun getProducts(): Call<ProductList>
    @GET("products")
    fun getProducts(@Query("limit") limit:Int): Call<ProductList>
}