package com.example.daggerhiltmvvmretrofitroom.model.data

import com.google.gson.annotations.SerializedName

data class ProductRequest(
    val name: String,
    @SerializedName("data")
    val productData: ProductData

)