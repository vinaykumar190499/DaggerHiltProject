package com.example.daggerhiltmvvmretrofitroom.model.remote


import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponse
import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponseItem
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductRequest
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-type:application/json")
    @POST("objects")
    fun saveProduct(
        @Body productRequest: ProductRequest
    ): Call<ProductResponse>

    @GET("objects")
    fun fetchProducts(@Query("id") ids: List<String>): Call<List<FetchProductResponseItem>>
}