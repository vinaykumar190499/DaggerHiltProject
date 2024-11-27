package com.example.daggerhiltmvvmretrofitroom

import androidx.lifecycle.LiveData
import com.example.daggerhiltmvvmretrofitroom.model.Entity.Product
import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponse
import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponseItem
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductRequest
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductResponse
import retrofit2.Call

interface IRepository {
    val productResponse:LiveData<ProductResponse>
    val error:LiveData<String>
    fun syncLatestProducts(productRequest: ProductRequest)
    fun searchProductInDb(name:String): Product
    fun fetchProducts(ids:List<String>): Call<List<FetchProductResponseItem>>

}