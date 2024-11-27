package com.example.daggerhiltmvvmretrofitroom.model.remote

import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponse
import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponseItem
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductRequest
import retrofit2.Call
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: ApiService) {

    fun postProductInServer(productRequest: ProductRequest)= apiService.saveProduct(productRequest)
    fun fetchProducts(ids: List<String>): Call<List<FetchProductResponseItem>> = apiService.fetchProducts(ids)
}