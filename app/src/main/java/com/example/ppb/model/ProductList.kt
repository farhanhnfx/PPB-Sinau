package com.example.ppb.model

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("products")
    val data: List<Product>
)
