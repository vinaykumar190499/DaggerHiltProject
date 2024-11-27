package com.example.daggerhiltmvvmretrofitroom.model.data

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val id: String,
    val name: String,
    val createdAt: String,
    @SerializedName("data")
    val productData: ProductData
)