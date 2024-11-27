package com.example.daggerhiltmvvmretrofitroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daggerhiltmvvmretrofitroom.IRepository
import com.example.daggerhiltmvvmretrofitroom.model.Entity.Product
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductRequest
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val iRepository: IRepository):ViewModel() {
    fun syncLatestProduct(productRequest: ProductRequest) {
        iRepository.syncLatestProducts(productRequest)

    }
    fun searchProductInDb(name:String):Product{
        return iRepository.searchProductInDb(name)
    }

    val productResponse:LiveData<ProductResponse> = iRepository.productResponse
    val error:LiveData<String> = iRepository.error

}
